package functional_interface;

@FunctionalInterface
public interface DemoInterface {
	void method1();

	// void method2();
	// 如果声明俩个方法则报错：
	// Invalid '@FunctionalInterface' annotation;
	// DemoInterface is not a functional interface

	// 默认方法不算哦
	default void method3() {
	}
	
}
