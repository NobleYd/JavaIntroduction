package 常用类的实现;

import java.util.HashMap;
import java.util.Map;

public class 常用类的实现 {

	public static void main(String[] args) {

		hashMapTest();
	}

	public static void hashMapTest() {
		System.out.println("---");
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();

		System.out.println(m);

		m.put(1, 1);

		System.out.println(m);
		// m.put(2, 2);
		// m.put(3, 3);

	}

}
