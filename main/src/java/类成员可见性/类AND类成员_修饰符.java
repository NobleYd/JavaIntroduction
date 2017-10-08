package 类成员可见性;

//class: public, abstract, final(final类不允许被继承)
public abstract class 类AND类成员_修饰符 {

	// public,protected,private,默认
	// abstract, static, final(final类型必须初始化，final方法不允许重写)
	// 作为成员的class可以使用所有的修饰符（包括static）
	static class c1 {
	}

	class c2 {
	}

	abstract class c3 {
	}

	final class c4 {
	}

	public class c5 {
	}

	protected class c6 {
	}

	private class c7 {
	}

	// abstract 与 final 不能同时修饰任何一个类（不仅仅是作为成员情况，所有情况都是）
	// 因为final类不允许被继承，而abstract不允许被实例化。这明显是冲突的，没有任何意义。
	// abstract final class c8{}

	// 下面俩例子可以测试随意更改修饰符的次序不影响，也就是说语法上不限制修饰符之间的次序
	static abstract protected class c8 {
	}

	static final protected class c9 {
	}

	// 注意这个特点和普通成员方法不一样，class是允许private类型的抽象类的
	// 因为即使是private，也是可能抽象，可以被实现的，比如本类中其他类实现
	// 而对于方法，只有子类能进行重写，如果是private则不可见了。
	private abstract class c10 {
	}

	// 修饰方法

	// 默认,public,protected,private

	void m1() {
	}

	public void m2() {
	}

	protected void m3() {
	}

	private void m4() {
	}

	// static
	static void m5() {
	}

	// 下面顺便测试了修饰符的次序，不影响
	public static void m6() {
	}

	static private void m7() {
	}

	// final 方法不允许被重写，这个类似 final class不允许被继承
	public final void m8() {
	}

	//
	abstract void m9();
	// 抽象方法不允许是final，和class的情况一样，一个是必须被重写，一个是不能重写，冲突
	// abstract final void m10();
	// 抽象方法不允许是私有访问权限，这个也很简单，因为抽象以为着要被重写，private是不可见的，如何重写呢？
	// private abstract void m11();

	// 成员变量
	// public,protected,private,默认
	public int a0;
	protected int a1;
	private int a2;
	int a3;
	// final( 必须初始化 )
	public final int a4 = 5;
	static protected final int a5 = 6;

	// transient,volitale

	public static void main(String[] args) {

		new c0();
		// new 成员变量修饰符().new c2();

	}

}

// 注意，不论是否public类型的类都不允许添加static修饰符。
// 只有作为类成员的内部类才可以使用static
// 在作为类成员的情况可以参考普通的成员的意义，包括访问权限等都是。
// 此处只能添加：abstract,final,(因为不是同名文件所以不能添加public)
class c0 {
}
