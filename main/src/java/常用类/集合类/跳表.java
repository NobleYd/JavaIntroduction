package 常用类.集合类;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class 跳表 {

	public static void main(String[] args) {

		// 注意内部非map，而是通过跳表实现。
		ConcurrentSkipListMap<Integer, Integer> m = new ConcurrentSkipListMap<Integer, Integer>();
		m.put(2, 2);
		m.put(1, 1);
		m.put(3, 3);
		m.put(0, 0);
		m.put(2, 2);
		// m.put(null, 4);//Exception in thread "main" java.lang.NullPointerException
		// 个人认为之所以不允许null的原因在于null在实现中有特殊用处。
		System.out.println(m);

		// ConcurrentSkipListSet就是使用ConcurrentSkipListMap实现的set
		ConcurrentSkipListSet<Integer> set;
		
		
		ConcurrentSkipListSet<Integer> concurrentSkipListSet;
		
		
	}

}
