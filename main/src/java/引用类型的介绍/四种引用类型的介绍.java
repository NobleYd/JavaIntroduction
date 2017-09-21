package 引用类型的介绍;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/***
 * 
 * 强引用／弱引用／软引用／虚引用
 * 
 * @author nobleyd
 *
 */
public class 四种引用类型的介绍 {

	public static void main(String[] args) {

		// 我们通过的引用都是强引用

		// 弱，软，虚引用都需要单独声明对应的类类型变量才可以创建。


		// 软引用(在OOM之前清楚 out of memory)
		SoftReference<Integer> softReference = new SoftReference<Integer>(new Integer(1));

		// 弱引用(下次gc清楚)
		WeakReference<Integer> weakReference = new WeakReference<Integer>(new Integer(1));
		
		//虚引用(仅仅用于跟踪对象的销毁，任何一个gc都会清理仅仅被虚引用引用的对象)
		PhantomReference<Integer> phantomReference = new PhantomReference<Integer>(new Integer(1), new ReferenceQueue<>());
		
		
		
	}

}
