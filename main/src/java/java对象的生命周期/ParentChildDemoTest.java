package java对象的生命周期;

public class ParentChildDemoTest {

	static {
		System.out.println("入口类静态代码块 1 执行");
	}

	{
		System.out.println("入口类构造代码块 1 执行");
	}

	static {
		finalStaticDemo = new Demo("入口类静态代码块 执行 final static demo");
		// System.out.println(finalStaticDemo);
		// -->上一行代码编译报错如下：
		// Cannot reference a field before it is defined
		// 可见，任何一个变量，使用代码块初始化 可以 出现在 定义声明之前。
		// 但却不代表可以使用。

	}

	private Demo demo = new Demo("入口类普通成员变量1 初始化");
	private static Demo staticDemo = new Demo("入口类静态成员变量初始化");

	private final static Demo finalStaticDemo;
	// 注意final类型static变量要么初始化，要么在静态代码块中初始化，否则编译报错如下：
	// The blank final field finalStaticDemo may not have been initialized

	{
		demo2 = new Demo("入口类普通成员变量2 初始化");
		//System.out.println(demo2);
		//-->
		//Cannot reference a field before it is defined
		//这个和finalStaticDemo同一个道理。
	}
	
	private Demo demo2;
	
	{
		demo3 = new Demo("demo3");
	}
	private final Demo demo3;
	//相对于静态的final成员，普通的非静态final成员的初始化则多了一个选择：构造函数中初始化。
	//静态final要么声明定义时初始化；要么静态代码块初始化。
	//普通final成员要么声明定义时初始化；要么代码块初始化；要么构造函数初始化。
	
	static {
		System.out.println("入口类静态代码块 2 执行");
	}

	{
		System.out.println("入口类构造代码块 2 执行");
	}

	//那么究竟 声明定义成员 和 代码块中初始化成员 为什么可以不管次序呢？
	//下面我们继续测试。
	{
		demo4 = new Demo("Demo4 initial in code block...");
	}
	private Demo demo4 = new Demo("Demo4 initial when defined");
	
	private Demo demo5 = new Demo("Demo4 initial when defined");
	{
		demo5 = new Demo("Demo5 initial in code block...");
	}
	//demo4和demo5的说明见main中。
	
	public ParentChildDemoTest() {
		super();
		System.out.println("入口类构造函数执行");
	}

	public static void main(String[] args) {
		System.out.println("入口类Main函数第一句代码执行");

		System.out.println(ParentChildDemoTest.finalStaticDemo);
		//不为null
		//注意这个是测试静态成员，先于定义使用静态代码块执行初始化情况。
		//是否能初始化成功的。这个输出不为null说明可以成功。
		
		System.out.println("---------------------------------");
		
		ChildDemo childDemo = new ChildDemo();

		/***
		 * 下面使用分号表示按照代码出现次序执行，逗号表示先后次序。
		 * 
		 * 注意，类是在刚开始加载的时候就执行了 静态成员初始化;静态代码块执行 （按照代码中次序执行） 。
		 * 
		 * 然后真正创建对象的时候按照如下次序执行： 父类普通成员初始化;父类构造代码块执行，父类构造函数执行 子类普通成员初始化;子类构造代码块执行，子类构造函数执行
		 * 
		 * ------- 从一定程度上讲，父类和子类的静态成员和静态代码块的执行并无次序。 这个程度是指父类和子类都是一个类而已。 但实际是都是父类先执行，这个可能是由于jdk加载和发现类的次序方式可能与这些继承关系有关。
		 * 
		 * ------- 还有一点就是main方法所在主类的静态成员和静态代码块执行要先于main方法第一句代码执行。 这个没啥意外，因为要先加载主类（执行静态成员初始化和静态代码块），才能执行其中的静态方法main。
		 * 
		 */

		/***
		 * 下面这个是简单的一个总结：
		 * 
		 * 首先是类的加载时候，则包括本类，父类的静态元素都要被执行（静态成员，静态代码块等等）。 然后是实例化对象的时候，本类以及父类的非静态元素才执行（所以肯定很确定静态的先于非静态）。
		 * 
		 * 其次，静态不存在构造函数。非静态中存在构造函数。在非静态的元素中，构造函数最后执行。
		 * 
		 * 所以，下面是总体次序：
		 * 
		 * 加载阶段： 父类静态元素（静态成员 和 静态代码块）按照出现次序依次执行。 子类静态元素（静态成员 和 静态代码块）按照出现次序依次执行。 实例化阶段： 父类非静态元素（成员 和 代码块）按照出现次序依次执行。 父类构造函数执行。 子类非静态元素（成员 和
		 * 代码块）按照出现次序依次执行。 子类构造函数执行。
		 */
		System.out.println("-----------------------");
		ParentChildDemoTest test = new ParentChildDemoTest();
		System.out.println(test.finalStaticDemo);
		System.out.println(test.demo2);
		//此处打印都不是null
		//说明，即使是在定义和声明 之前 通过代码块进行初始化 也是可以初始化成功的。
		//注意：但是先于定义使用变量却是不可以的。
		System.out.println(test.demo3);
		//不为null
		
		System.out.println(test.demo4.getTitle());//Demo4 initial when defined
		System.out.println(test.demo5.getTitle());//Demo5 initial in code block...
		//->从输出以及Demo构造函数输出次序。明显都可以发现普通成员和代码块仍然按照我们之前说的代码次序执行。
		//此处一旦做一个大胆的猜想。这个也不算，应该就是这样的。
		//但是这个我们无法做测试。
		//不论是静态还是非静态成员。
		//实际首先是分配内存。然后第一次初始化清空（即清零，0，false，null等）。
		//--->这个也和另外一本书中说的java对非同步的多线程代码保证最低安全性的问题。
		//----最低安全性 指 java 中线程任何时候读取一个共享变量的值，要么是null，要么是之前某个线程写入的。
		//----保证不会是凭空产生的值。要保证这个比如要清零初始化，否则可能出现随机值。
		
		//在完成这个清零操作之后。才开始按照代码次序做初始化。
		//即代码块和定义初始化部分 按照 代码出现次序 执行。

	}

}
