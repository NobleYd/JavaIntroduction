package java对象的生命周期;

public class 手动加载 {

	public static void main(String[] args) {

		// System.out.println(ChildDemo.staticString);
		
		// 仅仅执行下面这句话，因为仅仅引用了父类的static变量，所以子类并不会被初始化。
		//System.out.println(ChildDemo.staticStringInParent);
	
		
		try {
			Class.forName("java对象的生命周期.ChildDemo");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
