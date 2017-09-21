package 继承;

public class Parent {

	public void m1() {
		System.out.println("Parent.m1()");
	}

	private void m2() {
		System.out.println("Parent.m2()");
	}

	protected void m3() {
		System.out.println("Parent.m3()");
	}

	public final void m4() {
		System.out.println("Parent.m4()");

	}

	protected final void m5() {
		System.out.println("Parent.m5()");
		//注意此处无法调用到Parent的m1方法。
		m1();
		this.m1();
		Parent.this.m1();
	}

	private final void m6() {
		System.out.println("Parent.m6()");
	}

}
