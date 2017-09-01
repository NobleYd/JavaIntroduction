package java对象的生命周期;

public class ParentDemo {

	private Demo demo = new Demo("父普通成员变量初始化");
	private static Demo staticDemo = new Demo("父静态成员变量初始化");

	static {
		System.out.println("父静态代码块执行");
	}

	{
		System.out.println("父构造代码块执行");
	}

	public ParentDemo() {
		super();
		System.out.println("父构造函数执行");
	}

}
