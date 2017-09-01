package 反射;

public class 测试反射是否调用构造函数 {

	static class DemoClass {

		public DemoClass() {
			super();
			System.out.println("测试反射是否调用构造函数.DemoClass.DemoClass()");
		}
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {

		DemoClass demo = DemoClass.class.newInstance();
		// 结果证明是要调用的。
		
		//但是clone 方法是不调用的。
	}

}
