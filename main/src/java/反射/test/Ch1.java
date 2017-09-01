package 反射.test;

import java.lang.reflect.Method;

import 反射.bean.MyObject;

public class Ch1 {

	public static void main(String[] args) {
		// 获取所有方法，包括所有继承的方法。
		// 但是注意只有public方法。。。。
		Method[] methods = MyObject.class.getMethods();

		for (Method method : methods) {
			System.out.println("method = " + method.getName());
		}

		// 本类
		// method = method
		// method = staticMethod
		// 父类
		// method = parentStaticMethod
		// method = parentMethod
		// Object类的也算哦
		// method = wait
		// method = wait
		// method = wait
		// method = equals
		// method = toString
		// method = hashCode
		// method = getClass
		// method = notify
		// method = notifyAll

		System.out.println("------");

		// 下面这个方法只获取本类声明的方法。
		// 注意是所有方法，包括private、protected

		methods = MyObject.class.getDeclaredMethods();

		for (Method method : methods) {
			System.out.println("method = " + method.getName());
		}

		// method = method
		// method = staticMethod
		// method = privateMethod
		// method = protectedMethod

	}

}
