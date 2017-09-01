package 反射.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import 反射.bean.MyObject;

public class SomeInfo {

	@Test
	public void test1() {
		// 获取Constructor对象
		Constructor<?>[] constructors = MyObject.class.getConstructors();
		System.out.println(Arrays.asList(constructors));

		// MyObject.class.getConstructor(parameterTypes);

	}

	@Test
	public void test2() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<MyObject> constructor = MyObject.class.getConstructor();
		System.out.println(constructor);
		System.out.println(constructor.newInstance());
	}

	@Test
	public void test3() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 获取变量
		// MyObject.class.getField("");

		// 获取方法
		// MyObject.class.getMethod("");

		// 私有变量、私有方法访问（declared获取）
		// eg
		Method method = MyObject.class.getDeclaredMethod("privateMethod");
		// 下面这句因为是私有方法所有会报错
		// method.invoke(new MyObject());
		// 首先更改可见性，然后访问
		method.setAccessible(true);
		method.invoke(new MyObject());

		// getter setter
		for (Method m : MyObject.class.getMethods()) {
			if (m.getName().startsWith("get")) {
				// ...
			}
		}
	}

	@Test
	public void test4() {

	}

	@Test
	public void test5() {

	}

}
