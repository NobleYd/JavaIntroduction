package 反射.bean;

public class MyObject extends ParentObject implements Foo{

	private String name;

	public static void staticMethod() {
		System.out.println("MyObject.staticMethod()");
	}

	public void method() {
		System.out.println("MyObject.method()");
	}

	private void privateMethod() {
		System.out.println("MyObject.privateMethod()");
	}

	protected void protectedMethod() {
		System.out.println("MyObject.protectedMethod()");
	}

}
