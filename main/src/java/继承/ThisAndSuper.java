package 继承;

/***
 * 
 * this表示当前对象的引用。
 * 
 * super是指向父类对象的引用。
 * 
 * @author nobleyd
 *
 */
public class ThisAndSuper {

	public static void main(String[] args) {
		test1();

		// 下面的测试2用于说明一个关于this的存在作用域问题。
		test2();
	}

	public static void test1() {
		Parent p = new Child();

		p.m1();
		p.m3();
		p.m4();
		p.m5();

	}

	/***
	 * 1 静态方法由于不需要对象就可以调用，所以不存在this引用。
	 * 
	 * 2 普通方法this存在。
	 * 
	 * 3 我们以前（Android）中经常遇到ClassName.this的用法，这是因为有嵌套导致的this无法明确是哪个的时候要使用。
	 */
	public static void test2() {

	}

	// --->>>
	static class testThisArea {
		public static void m1() {
			// 1 此处this不存在
		}
		public void m2(){
			System.out.println(this);
		}
		
		static class testInnderStatic{
			public static void m3(){
				//this不存在
			}
			public void m4(){
				//3 （注意）：此处的this是不需要加类前缀的，因为这个this只有一个可能，就是 testInnderStatic 类。
				//对比情况4即可
				System.out.println(this);
			}
		}
		
		class testInnerCls{
			public void m5(){
				//4 注意这个地方的this有俩个情况
				//所以为了避免歧义是可以明确指出的。
				//为什么呢？因为 testInnerCls 类是非静态的类，也就是必须存在外部包含类的对象，才可能存在这个类的对象。
				//所以一旦进入到这个方法m5内，当前必定有俩个实例对象的存在。
				//一个是本类的实例对象，即用于调用m5方法的对象
				//还有一个是外部包含类的对象。
				System.out.println(testInnerCls.this);
				System.out.println(testThisArea.this);
			}
		}
		

	}

}
