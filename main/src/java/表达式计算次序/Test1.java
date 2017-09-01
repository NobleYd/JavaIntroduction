package 表达式计算次序;

public class Test1 {

	public static void main(String[] args) {

		int i = 1;

		System.out.println(i++ + i);
		i = 1;
		System.out.println((i++) + i);
		i = 1;
		System.out.println((++i) + i);
		i = 1;
		System.out.println((++i) + i);

		// 注意，java中不存在c中很多的表达式左右问题好像。
		// 从左到右，括号中先执行。比如i++虽然是后置++，但是放入括号中，也会在取值后立即++，而不是整个表达式赋值后才++
		// 也即同一个表达式中第一个后置++会影响到后续的i的值
		i = 1;
		System.out.println((i++) + (i++) + (++i));// 1+2+4

	}

}
