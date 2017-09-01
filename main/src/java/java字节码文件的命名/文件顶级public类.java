package java字节码文件的命名;

import java.util.Comparator;

public class 文件顶级public类 {

	// 文件顶级public类.class

	public static class 静态成员public类 {
		// 文件顶级public类$静态成员public类.class
	}

	static class 静态成员default类 {
		// 文件顶级public类$静态成员default类.class
	}

	protected static class 静态成员protected类 {
		// 文件顶级public类$静态成员protected类.class
	}

	private static class 静态成员private类 {
		// 文件顶级public类$静态成员public类.class
	}

	public class 普通成员public类 {
		// 文件顶级public类$普通成员public类.class
	}

	static class 普通成员default类 {
		// 文件顶级public类$普通成员default类.class
	}

	protected class 普通成员protected类 {
		// 文件顶级public类$普通成员protected类.class
	}

	private class 普通成员private类 {
		// 文件顶级public类$普通成员public类.class
	}

	public void 匿名类测试方法() {
		new Object() {
			// 文件顶级public类$1.class
		};
		new Object() {
			// 文件顶级public类$2.class
		};
		for (int i = 0; i < 5; i++) {
			new Object() {
				// 文件顶级public类$3.class
			};
		}
		new Comparator<Integer>() {
			// 文件顶级public类$4.class
			@Override
			public int compare(Integer o1, Integer o2) {
				return 0;
			}
		};
	}

	interface 普通成员接口 {

	}

	static interface 静态成员接口 {

	}

}

class 文件顶级default类 {
	// 文件顶级default类.class
}

interface 文件顶级接口 {

}
