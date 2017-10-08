package 基础语言;

public class 数组的定义 {

	public static void main(String[] args) {

		// 首先[]和变量名字的次序不要紧。
		int[] a;
		int b[];
		// 其次，多维数组的多个[]和变量名字的次序也不要紧。
		int[][] c;
		int[] d[];
		int e[][];
		// 数组每个维度的size的定义必须先有前边的维度才能定义后边的维度。
		// 注意，维度的size只有new的时候才指定，声明定义的时候不需要哦，否则报错。
		// int f[][] = new int[][];// Variable must provide either dimension expressions or an array initializer
		// int f[][] = new int[][5];//Cannot specify an array dimension after an empty dimension
		int f1[][] = new int[2][];
		int f2[][] = new int[2][3];
		int[] f3[] = new int[2][3];
		// ghijklmnopq

	}

}
