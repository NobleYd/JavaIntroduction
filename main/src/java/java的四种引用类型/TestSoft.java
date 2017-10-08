package java的四种引用类型;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/***
 * 测试软引用。
 * 
 *
 */
public class TestSoft {

	static class DemoObject {
		public String title;

		public int[] data = new int[10 * 1024 * 1024];// 10M

		public DemoObject(String title) {
			super();
			this.title = title;
		}

	}

	public static void main(String[] args) {

		// m1();// Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

		// m2();// exit end

	}

	public static void m1() {
		// 构造方式1
		DemoObject demoObject1 = new DemoObject("demoObject1");
		SoftReference<DemoObject> softReference1 = new SoftReference<TestSoft.DemoObject>(demoObject1);
		// 构造方式2
		DemoObject demoObject2 = new DemoObject("demoObject2");
		ReferenceQueue<DemoObject> referenceQueue2 = new ReferenceQueue<DemoObject>();
		SoftReference<DemoObject> softReference2 = new SoftReference<TestSoft.DemoObject>(demoObject1, referenceQueue2);

		// 辅助
		List<int[]> list = new ArrayList<int[]>();
		// 循环
		Reference<? extends DemoObject> ref = null;
		// ==null说明还没被回收
		while ((ref = referenceQueue2.poll()) == null) {
			// new内存。一次分配4M。
			list.add(new int[1024 * 1024]);
			System.out.println("当前共计使用" + list.size() * 4 + "M内存");
		}
	}

	public static void m2() {
		// 构造方式1
		DemoObject demoObject1 = new DemoObject("demoObject1");
		SoftReference<DemoObject> softReference1 = new SoftReference<TestSoft.DemoObject>(demoObject1);
		// 构造方式2
		DemoObject demoObject2 = new DemoObject("demoObject2");
		ReferenceQueue<DemoObject> referenceQueue2 = new ReferenceQueue<DemoObject>();
		SoftReference<DemoObject> softReference2 = new SoftReference<TestSoft.DemoObject>(demoObject1, referenceQueue2);

		// 不同之处：
		demoObject1 = null;
		demoObject2 = null;

		// 辅助
		List<int[]> list = new ArrayList<int[]>();
		// 循环
		Reference<? extends DemoObject> ref = null;
		// ==null说明还没被回收
		while ((ref = referenceQueue2.poll()) == null) {
			// new内存。一次分配4M。
			list.add(new int[1024 * 1024]);
			System.out.println("当前共计使用" + list.size() * 4 + "M内存");
		}
		System.out.println("exit end");
	}

}
