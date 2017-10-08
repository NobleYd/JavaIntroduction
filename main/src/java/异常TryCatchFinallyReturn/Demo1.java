package 异常TryCatchFinallyReturn;

/***
 * 当finall块中包含return语句时，Eclipse会给出警告“finally block does not complete normally”，原因分析如下：
 * 
 * 1、不管try块、catch块中是否有return语句，finally块都会执行。 <br/>
 * 2、finally块中的return语句会覆盖前面的return语句（try块、catch块中的return语句），所以如果finally块中有return语句，Eclipse编译器会报警告“finally block does not complete normally”。<br/>
 * 3、如果finally块中包含了return语句，即使前面的catch块重新抛出了异常，则调用该方法的语句也不会获得catch块重新抛出的异常，而是会得到finally块的返回值，并且不会捕获异常。
 *
 */
public class Demo1 {

	/***
	 * 测试try。catch。无finally。
	 */
	public static int m1(String number) {
		System.out.println("Demo1.m1(" + number + ")");
		int var = 0;
		try {
			System.out.println("try start...");
			var = Integer.parseInt(number);
			System.out.println("try end...");
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			var = -1;
		}
		return var;
	}

	/***
	 * 测试try／catch／finally
	 */
	public static int m2(String number) {
		int var = 0;
		try {
			System.out.println("try start...");
			var = Integer.parseInt(number);
			System.out.println("try end...");
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			var = -1;
		} finally {
			System.out.println("finally");
			var = -2;
		}
		return var;
	}

	/***
	 * 测试return。
	 */
	public static int m3(String number) {
		try {
			System.out.println("try start...");
			int var = Integer.parseInt(number);
			System.out.println("try end...");
			return var;
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			return -1;
		} finally {
			System.out.println("finally");
			return -2;// finally block does not complete normally
		}
		// return -3;//此处代码执行不到。
	}

	public static void main(String[] args) {
		// System.out.println(m1("12"));// 输出12
		// System.out.println(m1("1.2"));// -1
		// System.out.println(m2("12"));// -2
		// System.out.println(m2("1.2"));//-2
		// System.out.println(m3("12"));//-2(finally中return会覆盖try中的return。)
		// System.out.println(m3("1.2"));//-2(finally中return会覆盖catch中的return。)

	}

}
