package jdk8_接口的默认和静态方法;

public class 接口的继承问题 {

	interface a {
	}

	interface b {
	}

	// 注意接口之间的继承使用extends
	// 并且可以多继承
	interface c extends a, b {

	}

	// 类实现接口的也可以多个实现，使用implements
	class d implements a, b {

	}

}
