package 常用类.集合类.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class 综述 {

	/***
	 * 默认容量Cap为16<br/>
	 * 默认加载因子为0.75<br/>
	 * 默认threshold即：16*0.75 --> 12<br/>
	 * 即添加第13个元素会导致resize（cap和thres都翻倍）<br/>
	 * <br/>
	 */
	public static void hashMap() {
		Map<String, String> m = new HashMap<String, String>();
		for (int i = 1; i <= 100; i++) {
			if (i == 13) {// i=12的添加之后会进行resize
				System.out.println("breakpoint i = " + i);
			}
			m.put("key" + i, "value" + i);
		}
	}

	public static void hashMapNull() {
		Map<String, String> m = new HashMap<String, String>();
		System.out.println("size: " + m.size());
		m.put(null, null);
		System.out.println("size: " + m.size());
		m.put(null, null);
		System.out.println("size: " + m.size());
	}

	/***
	 * HashTable的key和value都不能为null。
	 */
	public static void hashTableNull() {
		Map<String, String> m = new Hashtable<String, String>();
		System.out.println("size: " + m.size());
		m.put("key", null);// Exception in thread "main" java.lang.NullPointerException
		m.put(null, "value");// Exception in thread "main" java.lang.NullPointerException
		System.out.println("size: " + m.size());
	}

	public static void concurrentHashMap() {

		ConcurrentHashMap<String, String> m = new ConcurrentHashMap<String, String>();
		m.put(null, "value");// Exception in thread "main" java.lang.NullPointerException
		m.put("key", null);// Exception in thread "main" java.lang.NullPointerException

		// jdk1.7之前是通过segment分组，每个segment对象作为对应分组的锁对象。
		// 1.8换成了使用每个bin中的第一个对象作为锁。

	}

	public static void main(String[] args) {
		// hashMap();
		// hashMapNull();
		// hashTableNull();
		concurrentHashMap();
	}
	// 关于hashMap-------
	// HashMap中有很多bin，每个bin中的存储是链式，或者红黑树。
	// 超过一定数量则转换为红黑树，少于一定数量则转换为链式。
	// 同一个hash位置的多个key链接，新增结点出现于头部（头插法）。

	// 默认的红黑树转换临界值
	// TREEIFY_THRESHOLD=8;（添加元素后成为8个则需要调用treeifyBin方法转换）
	// UNTREEIFY_THRESHOLD=6；
	// MIN_TREEIFY_CAPACITY=64；
	// treeifyBin 方法可自己看看注释。
	// --->>>和我们想的不一样。
	// treefyBin中还会判断bin的数量。如果bin的数量也就是table数组的大小小于MIN_TREEIFY_CAPACITY
	// 则只会resize整个table数组，而不会转换为红黑树。
	// 只有table的size大于这个值，并且单个bin的数量也达到要求才会被转为红黑树。
}
