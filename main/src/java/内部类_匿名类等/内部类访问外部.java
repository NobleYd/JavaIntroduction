package 内部类_匿名类等;

public class 内部类访问外部 {
	private String str1 = "str1";

	// 例子1
	class clsA {
		public void m1() {
			System.out.println(str1);
		}
	}

	// 例子2
	/***
	 * 注意类成员是可以直接访问的。而局部变量必须是final。 jdk8中默认访问是可以的，直接当作final。如果出现更改final变量才认为其不是final。
	 */
	public void test() {
		String str2 = "hello";
		str2 = "";
		new Object() {
			public void sayHello() {
				System.out.println(str1);
				// System.out.println(str2);// Local variable str2 defined in an enclosing scope must be final or effectively final
			}
		};

	}

}
