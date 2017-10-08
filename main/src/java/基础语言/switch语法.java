package 基础语言;

public class switch语法 {
	static enum ABC {
		a, b, c
	};

	public static void main(String[] args) {

		byte b = 1;
		char c = ' ';
		short s = 2;
		int i = 3;
		long l = 4;
		ABC enumABC;
		String str = "";
		// Cannot switch on a value of type long. Only convertible int values, strings or enum variables are permitted
		switch (i) {
		case 1:
			break;
		default:
			break;
		}

	}

}
