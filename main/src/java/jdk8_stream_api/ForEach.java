package jdk8_stream_api;

import java.util.ArrayList;
import java.util.List;

/***
 * Iterable提供了一个default方法forEach
 * 
 * @author nobleyd
 *
 */
public class ForEach {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");

		list.forEach(x -> System.out.println(x));

	}

}
