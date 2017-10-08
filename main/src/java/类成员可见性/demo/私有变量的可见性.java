package 类成员可见性.demo;

public class 私有变量的可见性 {

	private String name = "我是一个私有变量";

	public static void main(String[] args) {
		私有变量的可见性 t = new 私有变量的可见性();
		System.out.println(t.name);

		Animal animal = new Animal();
		//System.out.println(animal.name);// 编译错误。注意此处说明仅仅是本类可用，而不是本文件可用。
		System.out.println(animal.name2);// 包访问权限是本包内访问，即使不是一个文件，只有同属于一个包即可。
		// 对应还有protected是子类可见
		// public都可见
	}

}

class Animal {
	private String name = "我是一个Animal私有变量";
	String name2 = "我是一个Animal包访问权限变量";
}
