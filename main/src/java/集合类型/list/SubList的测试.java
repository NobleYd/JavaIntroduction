package 集合类型.list;

import java.util.ArrayList;
import java.util.List;

public class SubList的测试 {

	public static void main(String[] args) {
		testSubList();
	}

	public static void testSubList() {
		// 初始化一个list
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		// 创建子List
		List<String> subList = list.subList(1, 3);

		printTwoList(list, subList);
		// -->
		// list: [0, 1, 2, 3, 4]
		// subList: [1, 2]

		// subList:非结构化修改
		subList.set(0, "0_new");
		printTwoList(list, subList);
		// -->
		// list: [0, 0_new, 2, 3, 4]
		// subList: [0_new, 2]

		// list:非结构化修改
		list.set(2, "2_new");
		printTwoList(list, subList);
		// -->
		// list: [0, 0_new, 2_new, 3, 4]
		// subList: [0_new, 2_new]

		// subList:结构化修改
		subList.add("subList_new");
		printTwoList(list, subList);
		// -->
		// list: [0, 0_new, 2_new, subList_new, 3, 4]
		// subList: [0_new, 2_new, subList_new]
		subList.remove(0);
		printTwoList(list, subList);
		// -->
		// list: [0, 2_new, subList_new, 3, 4]
		// subList: [2_new, subList_new]

		// list:结构化修改(list的结构化修改会导致subList失效，所以一旦再次使用subList就会报错)
		// list.add("list_new");
		// printTwoList(list, subList);
		// -->
		// java.util.ConcurrentModificationException

		// list.subList(0, 1).add("abc");
		// printTwoList(list, subList);
		// -->
		// java.util.ConcurrentModificationException
		//此处做一个简单说明：类似上一个测试，这个测试也报错。
		//自己看看源码可以发现，父list元素结构化变化会导致子list报这个异常。
		//而 list.subList(0, 1).add("abc"); 这句代码是子list修改导致父list的变化（modCount的变化）
		//如何再去使用subList的打印，则报错。
	}

	public static void printTwoList(List list, List subList) {
		System.out.println("list: " + list);
		System.out.println("subList: " + subList);
	}

}
