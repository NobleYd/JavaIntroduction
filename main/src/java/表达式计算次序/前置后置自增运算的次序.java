package 表达式计算次序;

public class 前置后置自增运算的次序 {

	public static void main(String[] args) {
		// 1
		int i = 1;
		System.out.println(i++ + i);// 3
		// 2
		i = 1;
		System.out.println((i++) + i);// 3

		// 3
		i = 1;
		System.out.println(++i + i);// 4
		// 4
		i = 1;
		System.out.println((++i) + i);// 4

		// 5
		i = 1;
		System.out.println(i++ + i++);// 3
		// 6
		i = 1;
		System.out.println((i++) + (i++));// 3

		// 7
		i = 1;
		System.out.println(i++ + i++ + i++);// 6
		// 8
		i = 1;
		System.out.println((i++) + (i++) + (i++));// 6

		// 注意点：1-2，3-4，5-6，7-8等价。
		// 规律：i++先用i，然后马上自增i。不会等整个表达式计算完成才自增。

		// 9
		i = 1;
		System.out.println((i++) + (i++) + (++i));// 1+2+4=7
		// 10
		i = 1;
		System.out.println(i++ + i++ + ++i);// 1+2+4=7
		// 11
		i = 1;
		System.out.println(i++ + i++ + -i);// 1+2-3=0

	}

}
