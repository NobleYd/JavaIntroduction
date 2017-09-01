package 反射.test;

import java.lang.reflect.Array;

import org.junit.Test;

public class ArrayReflect {

	// 创建一个数组
	@Test
	public void test1() {
		Class cls = int.class;
		Object objs = Array.newInstance(cls, 3);
		System.out.println(objs);
	}

	// 通过Java反射机制同样可以访问数组中的元素。
	// 具体可以使用Array.get(…)和Array.set(…)方法来访问。
	// 下面是一个例子
	@Test
	public void test2() {
		int[] intArray = (int[]) Array.newInstance(int.class, 3);

		Array.set(intArray, 0, 123);
		Array.set(intArray, 1, 456);
		Array.set(intArray, 2, 789);

		System.out.println("intArray[0] = " + Array.get(intArray, 0));
		System.out.println("intArray[1] = " + Array.get(intArray, 1));
		System.out.println("intArray[2] = " + Array.get(intArray, 2));
	}

	// 在我编写Butterfly DI Container的脚本语言时，
	// 当我想通过反射获取数组的Class对象时遇到了一点麻烦。
	// 如果不通过反射的话你可以这样来获取数组的Class对象：
	@Test
	public void test3() {
		Class stringArrayClass = String[].class;
		System.out.println(stringArrayClass);
	}

	// 如果使用Class.forName()方法来获取Class对象则不是那么简单。
	// 比如你可以像这样来获得一个原生数据类型（primitive）int数组的Class对象
	@Test
	public void test4() throws ClassNotFoundException {
		// Class cls1 = Class.forName("int");//-->ClassNotFound
		Class cls2 = Class.forName("[I");
		// Class cls3 = Class.forName("I");// -->ClassNotFound
		Class cls4 = Class.forName("[Ljava.lang.String;");
		// System.out.println(cls1);
		System.out.println(cls2);
		// System.out.println(cls3);
		System.out.println(cls4);

		Class stringArrayClass = Array.newInstance(String.class, 0).getClass();
		System.out.println("is array: " + stringArrayClass.isArray());
	}

	public Class getClass(String className) {
		if ("int".equals(className))
			return int.class;
		if ("long".equals(className))
			return long.class;
		
		//.......
		//...
		return null;
	}
	
	@Test
	public void test5(){
		String[] strings = new String[3];
		Class stringArrayClass = strings.getClass();
		Class stringArrayComponentType = stringArrayClass.getComponentType();
		System.out.println(stringArrayComponentType);
	}
	
	
}
