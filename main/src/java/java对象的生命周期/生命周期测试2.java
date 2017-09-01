package java对象的生命周期;

public class 生命周期测试2 {
	public static 生命周期测试2 t1 = new 生命周期测试2();
	{
		System.out.println("blockA");
	}
	static {
		System.out.println("blockB");
	}

	public static void main(String[] args) {
		System.out.println("hello");
		生命周期测试2 t2 = new 生命周期测试2();
	}
	// 输出如下
	// blockA
	// blockB
	// hello
	// blockA
}
