package java对象字节码;

/***
 * http://www.cnblogs.com/magialmoon/p/3757767.html
 * 
 * 可以看看Instrumentation的注释。目前没法测试。实际需要agent方式运行，能被自动注入一个instrumentation对象。
 * 
 * @author nobleyd
 *
 */
public class 一个java对象占用多大内存 {

	public static void main(String[] args) {
		t1();

		t2();
	}

	public static void t1() {
		System.out.println(SizeOfObject.sizeOf(new Integer(1)));
	}

	public static void t2() {
	}

}
