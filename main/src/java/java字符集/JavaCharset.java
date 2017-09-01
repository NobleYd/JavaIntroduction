package java字符集;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class JavaCharset {

	public static Charset defaultCharset = Charset.defaultCharset();
	public static Charset ISO8859_1 = Charset.forName("ISO8859_1");
	public static Charset GBK = Charset.forName("GBK");
	public static Charset UTF_8 = Charset.forName("UTF-8");
	public static Charset UTF_16 = StandardCharsets.UTF_16;

	public static Charset[] charsets = new Charset[] { defaultCharset, ISO8859_1, GBK, UTF_8, UTF_16 };

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	// length
	public static void test1() {
		System.out.println("JavaCharset.test1()---->");
		String str = "Java哦";
		System.out.println(str + ".length()=" + str.length());
		for (Charset charset : charsets) {
			System.out.println(str + ".getBytes(" + charset.name() + ").length()=" + str.getBytes(charset).length);
		}
		System.out.println(System.lineSeparator());
	}

	public static void test2() {
		System.out.println("JavaCharset.test2()---->");
		String str = "Java哦";
		for (Charset charset : charsets) {
			System.out.println("new String(" + str + ".getBytes(" + charset.name() + "), " + charset.name() + ")="
					+ new String(str.getBytes(charset), charset));
		}
		System.out.println(System.lineSeparator());
	}

	public static void test3() {
		System.out.println("JavaCharset.test3()---->");
		System.out.println(System.lineSeparator());
	}

}
