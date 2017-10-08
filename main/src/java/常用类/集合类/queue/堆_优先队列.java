package 常用类.集合类.queue;

import java.util.PriorityQueue;

/***
 * Java中的堆是：PriorityQueue。
 * 
 * PriorityQueue是一个基于优先级堆的无界队列，它的元素是按照自然顺序(natural order)排序的。
 * 
 * 在创建的时候，我们可以给它提供一个负责给元素排序的比较器。
 * 
 * PriorityQueue不允许null值，因为他们没有自然顺序，或者说他们没有任何的相关联的比较器。
 * 
 * 最后，PriorityQueue不是线程安全的，入队和出队的时间复杂度是O(log(n))。
 */
public class 堆_优先队列 {

	public static void main(String[] args) {

		// 堆的默认大小是11(默认从小到大)
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.offer(5);
		q.offer(2);
		q.offer(0);
		q.offer(1);
		q.offer(3);
		q.offer(1);
		q.offer(4);

		while (!q.isEmpty()) {
			System.out.println(q.poll());
		}

	}

}
