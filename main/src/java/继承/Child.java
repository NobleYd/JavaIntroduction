package 继承;

public class Child extends Parent {

	// 重写父类方法
	@Override
	public void m1() {

	}

	// 父类的私有方法无法被重写
	// @Override
	// private void m2() {
	// }

	// 重写父类的方法，访问权限可以放松，但不能更小
	// 思考原因：Parent p = new Child();
	// 这句代码，p.m3()是protected访问权限，但实际的child类的m3却是private。这不就出问题了嘛。
	// 所以访问权限必须一样或者放松。
	// 可以立即父类是提供接口，提供访问入口的。
	// 入口小，内部大门大可以
	// 入口大，内部小就不可以了，入口大准入了，结果进去发现不能进？尴尬了。
	@Override
	public void m3() {
		super.m3();
	}

	// final 方法无法重写
	// public void m4() {
	// }
	// final 方法无法重写
	// protected void m5() {
	// }

	// 注意子类无法访问到父类私有方法
	// 因此这个不算重写
	private final void m6() {
		
	}

}
