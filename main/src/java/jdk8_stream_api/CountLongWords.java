package jdk8_stream_api;

import java.util.Arrays;

/***
 * 统计长单词的个数
 * 
 * @author nobleyd
 *
 */
public class CountLongWords {

	public static void main(String[] args) {
		String str = "hello world, how are you!";
		long count = Arrays.stream(str.split(" ")).filter(x -> x.length() >= 5).count();
		System.out.println(count);

	}

}
