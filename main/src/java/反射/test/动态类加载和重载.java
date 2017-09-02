package 反射.test;

import org.junit.Test;

import 反射.bean.Foo;
import 反射.bean.MyObject;
import 反射.bean.ParentObject;

/***
 * <pre>
	类加载器
	所有Java应用中的类都是被java.lang.ClassLoader类的一系列子类加载的。
	因此要想动态加载类的话也必须使用java.lang.ClassLoader的子类。
	一个类一旦被加载时，这个类引用的所有类也同时会被加载。
	类加载过程是一个递归的模式，所有相关的类都会被加载。
	但并不一定是一个应用里面所有类都会被加载，与这个被加载类的引用链无关的类是不会被加载的，直到有引用关系的时候它们才会被加载。
	
	类加载体系
	在Java中类加载是一个有序的体系。
	当你新创建一个标准的Java类加载器时你必须提供它的父加载器。
	当一个类加载器被调用来加载一个类的时候，首先会调用这个加载器的父加载器来加载。
	如果父加载器无法找到这个类，这时候这个加载器才会尝试去加载这个类。
	
	类加载
	类加载器加载类的顺序如下：
	1、检查这个类是否已经被加载。
	2、如果没有被加载，则首先调用父加载器加载。
	3、如果父加载器不能加载这个类，则尝试加载这个类。

	当你实现一个有重载类功能的类加载器，它的顺序与上述会有些不同。
	类重载不会请求的他的父加载器来进行加载。在后面的段落会进行讲解。
	
	
	动态类重载
	动态类重载有一点复杂。
	Java内置的类加载器在加载一个类之前会检查它是否已经被加载。
	因此重载一个类是无法使用Java内置的类加载器的，如果想要重载一个类你需要手动继承ClassLoader。

	在你定制ClassLoader的子类之后，你还有一些事需要做。
	所有被加载的类都需要被链接。
	这个过程是通过ClassLoader.resolve()方法来完成的。
	由于这是一个final方法，因此这个方法在ClassLoader的子类中是无法被重写的。
	resolve()方法是不会允许给定的ClassLoader实例链接一个类两次。
	所以每当你想要重载一个类的时候你都需要使用一个新的ClassLoader的子类。
	你在设计类重载功能的时候这是必要的条件。
 * 
 * 
 * </pre>
 */
public class 动态类加载和重载 {

	@Test
	public void test1() {

		ClassLoader classLoader = 动态类加载和重载.class.getClassLoader();

		try {
			Class<?> cls = classLoader.loadClass("反射.bean.MyObject");
			System.out.println("name = " + cls.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ClassLoader currentClassLoader = this.getClass().getClassLoader();
		System.out.println("currentClassLoader=" + currentClassLoader);
		MyClassLoader myClassLoader = new MyClassLoader(currentClassLoader);
		System.out.println(myClassLoader.getClass().getClassLoader());
		System.out.println("myClassLoader.getClass().getClassLoader == currentClassLoader: "
				+ myClassLoader.getClass().getClassLoader().equals(currentClassLoader));

		// true
		// MyClassCloader类本身就是被currentClassLoader加载的。

		Class fooCls = myClassLoader.loadClass("反射.bean.Foo");
		System.out.println(fooCls);

		Class myObjectCls = myClassLoader.loadClass("反射.bean.MyObject");
		System.out.println(myObjectCls);
		MyObject object = (MyObject) myObjectCls.newInstance();
		// 报错：
		// 反射.bean.MyObject can not be cast to 反射.bean.MyObject
		// 原因是赋值号左边已经引用了MyObject，所以MyObject的加载器应该是当前加载器。
		// 而myObjectCls是MyClassLoader加载的，俩个版本不同。

	}

	@Test
	public void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ClassLoader currentClassLoader = this.getClass().getClassLoader();
		MyClassLoader myClassLoader = new MyClassLoader(currentClassLoader);

		System.out.println("currentClassLoader=" + currentClassLoader);
		System.out.println("myClassLoader=" + myClassLoader);

		Class myObjectCls = myClassLoader.loadClass("反射.bean.MyObject");
		// 先使用Object接收(不强制转换)
		Object object = myObjectCls.newInstance();
		// 打印object的class
		System.out.println(object.getClass().getClassLoader());// 可以发现是myClassLoader
		// 打印MyObject的加载器
		System.out.println(MyObject.class.getClassLoader());// 可以发现是currentClassLoader

		// 所以这是个死循环，你无法在当前程序内使用另一个加载器加载类A
		// 同时还在当前程序中直接引用到A。
		// 只能是通过加载出来的cls对象newInstance创建对象，而且不能如此赋值： A a = cls.newInstance();
		// 因为你这么写的时候currentClassLoader就会加载A，cls创建出来的是另一个版本的A
		// 无法赋值，也无法强制转换。

		// --->解决方式见下面的main方法。
		// 主要思路就是在当前程序中不直接引用A，而是引用A的接口或者父类。
		// 然后通过控制MyClassLoad使得A的接口，父类本身就是通过currentClassLoader加载的。
		// 这样就不会冲突。

	}

	// 一个类加载器 加载 一个类 最多一次
	// 一个类被多个类加载器加载后，类型不兼容（看测试2）
	public static void main(String[] args)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		System.out.println("动态类加载和重载.main()");

		ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
		MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
		Class myObjectClass = classLoader.loadClass("反射.bean.MyObject");

		Foo obj1 = (Foo) myObjectClass.newInstance();
		ParentObject obj2 = (ParentObject) myObjectClass.newInstance();
		System.out.println(obj1);
		System.out.println(obj2);

		// create new class loader so classes can be reloaded.
		classLoader = new MyClassLoader(parentClassLoader);
		myObjectClass = classLoader.loadClass("反射.bean.MyObject");

		obj1 = (Foo) myObjectClass.newInstance();
		obj2 = (ParentObject) myObjectClass.newInstance();

	}

}
