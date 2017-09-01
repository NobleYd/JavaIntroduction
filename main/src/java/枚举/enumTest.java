package 枚举;

public class enumTest {

	enum gender {
		male, female
	}

	public static void main(String[] args) {

		gender g = gender.female;

		System.out.println(g);

		System.out.println(g instanceof Object);// true
		System.out.println(g instanceof Enum);// true

		// 可见，任何一个enum类型都是Enum的子类。

	}

}
