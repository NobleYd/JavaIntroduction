package 异常TryCatchFinallyReturn;

public class Demo1 {

	public static void main(String[] args) {
		int ans = m1("1.2.3");
		System.out.println("ans = " + ans);

		System.out.println("------------------");

		ans = m2("1.2");
		System.out.println("ans = " + ans);
		
		System.out.println("------------------");

		ans = m1("12");
		System.out.println("ans = " + ans);
		
		
		System.out.println("------------------");

		ans = m2("12");
		System.out.println("ans = " + ans);
		//===>注意这个例子是用于说明当正常返回，无异常情况时。finally块的执行时机。
		//finally应该在return之前执行。真正的那个return会被忽略不执行。
	}

	public static int m1(String number) {
		int var = 0;
		try {
			System.out.println("try start...");
			var = Integer.parseInt(number);
			System.out.println("try end...");
			return 1;
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			return 2;
		}
		//return var;
	}

	public static int m2(String number) {
		int var = 0;
		try {
			System.out.println("try start...");
			var = Integer.parseInt(number);
			System.out.println("try end...");
			return 1;
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			return 2;
		} finally {
			System.out.println("finally");
			return 3;
		}
		//return var;
	}

}
