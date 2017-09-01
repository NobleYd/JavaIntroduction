package 反射.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.Test;

import 反射.bean.MyObject;

public class ClassInformationReflection {

	// Class对象的获取。
	@Test
	public void test0() throws ClassNotFoundException {
		// 1
		Class<MyObject> cls1 = MyObject.class;
		// 2
		Class<MyObject> cls2 = (Class<MyObject>) Class.forName("com.app.bean.MyObject");
		System.out.println(cls1);
		System.out.println(cls2);
	}

	// 类名：你可以从Class对象中获取两个版本的类名。
	@Test
	public void test1() {
		Class<MyObject> cls1 = MyObject.class;
		System.out.println("Name:" + cls1.getName());
		System.out.println("SimpleName:" + cls1.getSimpleName());
	}

	// 修饰符
	@Test
	public void test2() {
		Class<MyObject> cls1 = MyObject.class;
		int modifiers = cls1.getModifiers();
		System.out.println("modifiers:" + cls1.getModifiers());

		// 修饰符都被包装成一个int类型的数字，这样每个修饰符都是一个位标识(flag bit)
		// 可以使用java.lang.reflect.Modifier类中的方法来检查修饰符的类型：
		Modifier.isAbstract(modifiers);
		Modifier.isFinal(modifiers);
		Modifier.isInterface(modifiers);
		Modifier.isNative(modifiers);
		Modifier.isPrivate(modifiers);
		Modifier.isProtected(modifiers);
		Modifier.isPublic(modifiers);
		Modifier.isStatic(modifiers);
		Modifier.isStrict(modifiers);
		Modifier.isSynchronized(modifiers);
		Modifier.isTransient(modifiers);
		Modifier.isVolatile(modifiers);
	}

	// 包信息
	@Test
	public void test3() {
		Class<MyObject> cls1 = MyObject.class;
		Package package1 = cls1.getPackage();
		System.out.println(package1);
		// package1.getName();
		// ...
	}

	// 实现的接口
	@Test
	public void test4() {
		Class<?>[] interfaces = MyObject.class.getInterfaces();
		System.out.println(Arrays.asList(interfaces));
	}

	// 构造器
	@Test
	public void test5() {
		Constructor<?>[] constructors = MyObject.class.getConstructors();
		System.out.println(Arrays.asList(constructors));
	}

	// 方法
	@Test
	public void test6() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method method = MyObject.class.getMethod("staticMethod");
		System.out.println(method);
		method.invoke(null);
	}

	// 变量
	@Test
	public void test7() {
		// 只返回public类型的field哦。
		Field[] fields = MyObject.class.getFields();
		System.out.println(Arrays.asList(fields));
		// 下面这个方法返回所有可见性的方法，但是只返回本类声明的field，不包括继承来的。
		Field[] fields2 = MyObject.class.getDeclaredFields();
		System.out.println(Arrays.asList(fields2));
	}

	// 注解
	@Test
	public void test8() {
		Annotation[] annotations = MyObject.class.getAnnotations();
		System.out.println(Arrays.asList(annotations));
	}

}
