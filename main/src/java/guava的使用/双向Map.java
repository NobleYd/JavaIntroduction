package guava的使用;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class 双向Map {

	public static void main(String[] args) {

		BiMap<String, String> biMap = HashBiMap.<String, String>create();
		biMap.put("张三", "李四");
		biMap.put("一旦", "小悠");

		System.out.println(biMap);

		System.out.println(biMap.get("一旦"));
		System.out.println(biMap.get("李四"));
		System.out.println(biMap.inverse().get("李四"));
	}

}
