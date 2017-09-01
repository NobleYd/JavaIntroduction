package 等号_equals_hashcode_常量池等;

public class 等号与equals {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}

	// 基本数据类型
	public static void test1() {
		int int_a = 1;
		int int_b = 1;
		System.out.println("int_a==int_b: " + (int_a == int_b));// true

		Integer integer_a1 = 1;
		Integer integer_b1 = 1;
		System.out.println("integer_a1==integer_b1: " + (integer_a1 == integer_b1));// true

		Integer integer_a2 = -128;
		Integer integer_b2 = -128;
		System.out.println("integer_a2==integer_b2: " + (integer_a2 == integer_b2));// true
		Integer integer_a3 = 127;
		Integer integer_b3 = 127;
		System.out.println("integer_a3==integer_b3: " + (integer_a3 == integer_b3));// true

		Integer integer_a4 = -129;
		Integer integer_b4 = -129;
		System.out.println("integer_a4==integer_b4: " + (integer_a4 == integer_b4));// false
		Integer integer_a5 = 128;
		Integer integer_b5 = 128;
		System.out.println("integer_a5==integer_b5: " + (integer_a5 == integer_b5));// false

		System.out.println("------");

		int int_1 = 1;
		Integer integer_1 = 1;
		Integer integer_new_1 = new Integer(1);
		Integer integer_value_of_1 = Integer.valueOf(1);

		// int类型和任何类型比较都是比较真实数字的大小的
		System.out.println(int_1 == integer_1);// true
		System.out.println(int_1 == integer_new_1);// true
		System.out.println(int_1 == integer_value_of_1);// true

		// new的Integer是新的一个对象。
		// valueOf的Integer等价于int类型的1。（-128到127之内）
		System.out.println(integer_1 == integer_new_1);// false
		System.out.println(integer_1 == integer_value_of_1);// true

		int int_555 = 555;
		Integer integer_555 = 555;
		Integer integer_new_555 = new Integer(555);
		Integer integer_value_of_555 = new Integer(555);

		// int类型和任何类型比较都是比较真实数字的大小的
		System.out.println(int_555 == integer_555);// true
		System.out.println(int_555 == integer_new_555);// true
		System.out.println(int_555 == integer_value_of_555);// true

		// new的Integer是新的一个对象。
		// valueOf的Integer在范围外也相当于new的一个新对象。
		System.out.println(integer_555 == integer_new_555);// false
		System.out.println(integer_555 == integer_value_of_555);// false

	}

	static class DemoObject {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}

	// 对象类型数据
	public static void test2() {
		System.out.println("----object----");
		DemoObject o1 = new DemoObject();
		DemoObject o2 = new DemoObject();
		DemoObject o11 = o1;
		DemoObject o22 = o2;
		System.out.println(o1 == o2);// false
		System.out.println(o11 == o1);// true
	}

	static class DemoObject2 {
		public Integer id;

		public DemoObject2(Integer id) {
			super();
			this.id = id;
		}
		//
		// @Override
		// public int hashCode() {
		// final int prime = 31;
		// int result = 1;
		// result = prime * result + ((id == null) ? 0 : id.hashCode());
		// return result;
		// }

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DemoObject2 other = (DemoObject2) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

	}

	public static void test3() {
		System.out.println("equals");
		DemoObject2 demoObject1 = new DemoObject2(1314);
		DemoObject2 demoObject2 = new DemoObject2(1314);
		System.out.println(demoObject1 == demoObject2);// false
		System.out.println(demoObject1.equals(demoObject2));// true

		DemoObject2 demoObject3 = new DemoObject2(new Integer(123));
		DemoObject2 demoObject4 = new DemoObject2(new Integer(123));
		System.out.println(demoObject3 == demoObject4);// false
		System.out.println(demoObject3.equals(demoObject4));// true

		DemoObject2 demoObject5 = new DemoObject2(Integer.valueOf(123));
		DemoObject2 demoObject6 = new DemoObject2(Integer.valueOf(123));
		System.out.println(demoObject5 == demoObject6);// false
		System.out.println(demoObject5.equals(demoObject6));// true

	}

	static class DemoObject3 {
		public Integer id;

		public DemoObject3(Integer id) {
			super();
			this.id = id;
		}
		//
		// @Override
		// public int hashCode() {
		// final int prime = 31;
		// int result = 1;
		// result = prime * result + ((id == null) ? 0 : id.hashCode());
		// return result;
		// }

		@Override
		public boolean equals(Object obj) {
			DemoObject3 o2 = (DemoObject3) obj;
			return this.id == o2.id;
		}

	}

	public static void test4() {
		System.out.println("equals ( 使用==实现的equals ) ");
		DemoObject3 demoObject1 = new DemoObject3(1314);
		DemoObject3 demoObject2 = new DemoObject3(1314);
		System.out.println(demoObject1 == demoObject2);// false
		System.out.println(demoObject1.equals(demoObject2));// false

		DemoObject3 demoObject3 = new DemoObject3(new Integer(123));
		DemoObject3 demoObject4 = new DemoObject3(new Integer(123));
		System.out.println(demoObject3 == demoObject4);// false
		System.out.println(demoObject3.equals(demoObject4));// false

		DemoObject3 demoObject5 = new DemoObject3(Integer.valueOf(123));
		DemoObject3 demoObject6 = new DemoObject3(Integer.valueOf(123));
		System.out.println(demoObject5 == demoObject6);// false
		System.out.println(demoObject5.equals(demoObject6));// true

	}

}
