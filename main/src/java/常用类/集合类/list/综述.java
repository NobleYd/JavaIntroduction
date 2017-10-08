package 常用类.集合类.list;

import java.util.ArrayList;
import java.util.LinkedList;

public class 综述 {

	/****
	 * ArrayList的默认初始容量为10<br/>
	 * 每次扩容的时候变为原大小的1.5倍
	 */
	public static void arrayList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println(list.size());
		System.out.println(list.add(1));
	}

	/***
	 * LinkedList是链表，不存在预先分配的内存，也就不存在初始大小和扩容问题了。
	 * 
	 * LinkedList同时还是队列，因为实现了Queue接口。
	 * 
	 */
	public static void linkedList() {
		LinkedList<Integer> linkedList = new LinkedList<>();

	}

	public static void main(String[] args) {

	}

}
