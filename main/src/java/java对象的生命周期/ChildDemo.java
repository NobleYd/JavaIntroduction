package java对象的生命周期;

public class ChildDemo extends ParentDemo {

	//静态的都先于非静态执行
	
	static {
		System.out.println("静态 子类 代码块1 执行");
	}
	
	private static Demo staticDemo1 = new Demo("静态 子类 成员变量1 初始化");
	
	static {
		System.out.println("静态 子类 代码块2 执行");
	}
	
	private static Demo staticDemo2 = new Demo("静态 子类 成员变量2 初始化");
	
	private Demo demo1 = new Demo("非静态 子类 成员变量1 初始化");

	{
		System.out.println("非静态 子类 代码块1 执行");
	}
	
	{
		System.out.println("非静态 子类 代码块2 执行");
	}

	private Demo demo2 = new Demo("非静态 子类 成员变量2 初始化");

	public ChildDemo() {
		super();
		System.out.println("子类 构造函数 执行");
	}

}
