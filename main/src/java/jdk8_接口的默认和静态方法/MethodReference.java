package jdk8_接口的默认和静态方法;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

public class MethodReference {

	public static class Car {
		/***
		 * 返回参数中的supplier的get返回。
		 */
		public static Car getFromSupplier(final Supplier<Car> supplier) {
			return supplier.get();
		}

		/***
		 * 碰撞
		 */
		public static void collide(final Car car) {
			System.out.println(car + " collided");
		}

		// --------------下面的是非static方法

		/***
		 * 跟随
		 */
		public void follow(final Car another) {
			System.out.println(this + " follows " + another);
		}

		/***
		 * 修复
		 */
		public void repair() {
			System.out.println(this + " repaired");
		}
	}

	/**
	 * 第一种方法引用是构造器引用，它的语法是Class::new <br/>
	 * 或者更一般的Class< T >::new。请注意构造器没有参数。
	 */
	@Test
	public void test0() {

		final Car car = Car.getFromSupplier(Car::new);
		final List<Car> cars = Arrays.asList(car);
		System.out.println(car);
		System.out.println(cars);

		/***
		 * 第二种方法引用是静态方法引用，它的语法是Class::static_method。<br/>
		 * 请注意这个方法接受一个Car类型的参数。
		 * 
		 */
		cars.forEach(Car::collide);

		/***
		 * 第三种方法引用是特定类的任意对象的方法引用，它的语法是Class::method。<br/>
		 * 请注意，这个方法没有参数。
		 */
		cars.forEach(Car::repair);

		/***
		 * 最后，第四种方法引用是特定对象的方法引用，它的语法是instance::method。<br/>
		 * 请注意，这个方法接受一个Car类型的参数
		 */
		final Car police = Car.getFromSupplier(Car::new);
		cars.forEach(police::follow);
	}

}
