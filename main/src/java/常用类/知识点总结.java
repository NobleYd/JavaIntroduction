package 常用类;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * 主要总结一些简单的面试常问问题。
 * 
 * @author nobleyd
 *
 */
public class 知识点总结 {

	public static void arrayList() {
		// 默认容量10
		// 每次容量扩容时，变为当前容量的1.5倍。
		List<String> list1 = new ArrayList<String>();
		int initialCapacity = 10;
		List<String> list2 = new ArrayList<String>(initialCapacity);
	}

	public static void linkedList() {
		List<String> list1 = new LinkedList<String>();

	}

	public static void hashMap() {
		// 初始容量16
		// 默认加载因子0.75
		Map<String, String> m1 = new HashMap<String, String>();
		int initialCapacity = 16;
		Map<String, String> m2 = new HashMap<String, String>(initialCapacity);
		float loadFactor = 0.75F;
		Map<String, String> m3 = new HashMap<String, String>(initialCapacity, loadFactor);
	}

	public static void linkedHashMap() {

	}

	public static void treeMap() {

	}

	public static void hashSet() {

	}

	public static void treeSet() {

	}

	public static void arrayBlockingQueue() {

	}

	public static void linkedBlockingQueue() {

	}

	public static void main(String[] args) {
	}

}
