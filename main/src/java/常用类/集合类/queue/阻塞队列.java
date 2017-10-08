package 常用类.集合类.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class 阻塞队列 {

	/***
	 * 基于数组的阻塞队列(java.util.concurrent)
	 * 
	 * 【有界】：队列元素数量有限。 <br/>
	 * 【容量不可更改】：初始给定一个容量，然后就不可更改了。<br/>
	 * 【FIFO】<br/>
	 * 【阻塞】：往满队列插入元素/从空队列取元素 都会阻塞。<br/>
	 * @throws InterruptedException 
	 */
	public static void t1() throws InterruptedException {
		ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(10);// 必须指定容量
		arrayBlockingQueue.put(1);
		arrayBlockingQueue.take(); 
		
	}

	/***
	 * 基于链表的阻塞队列(java.util.concurrent)
	 * 
	 * 【可选有界】optionally-bounded：可以指定界限，也可以不指定。<br/>
	 * 【容量】：最大容量，可以指定，也可以不指定，默认为Integer的最大值。<br/>
	 * 【FIFO】<br/>
	 * 【阻塞】：往满队列插入元素/从空队列取元素 都会阻塞。<br/>
	 * @throws InterruptedException 
	 * 
	 */
	public static void t2() throws InterruptedException {
		// 基于链表的阻塞队列
		LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<Integer>(10);
		linkedBlockingQueue.put(1);
		linkedBlockingQueue.take();
		// 基于链表的阻塞双端队列
		LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<Integer>(10);
	}

	public static void main(String[] args) throws InterruptedException {
		t1();
		t2();

		// 默认初始容量大小11（注意仅仅值初始容量，不代表最大容量）【无界】
		PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<Integer>();

		// 元素都对应一个delay值，只有过了delay值的元素才能被take到
		// DelayQueue<Delayed> delayeds =null;

		// 同步队列，不存储元素的队列。
		// 每个take和offer完全匹配。
		SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<Integer>();
		synchronousQueue.put(1);
		synchronousQueue.take();
		
		// 链表结构的【无界】
		LinkedTransferQueue<Integer> linkedTransferQueue = new LinkedTransferQueue<Integer>();
		
	}
	
	// TransferQueue是一个继承了BlockingQueue的接口，并且增加若干新的方法。LinkedTransferQueue是TransferQueue接口的实现类，其定义为一个无界的队列，具有先进先出(FIFO)的特性。
	// 有人这样评价它："TransferQueue是是ConcurrentLinkedQueue、SynchronousQueue (公平模式下)、无界的LinkedBlockingQueues等的超集。"
	// LinkedTransferQueue实现了一个重要的接口TransferQueue，该接口含有下面几个重要方法：
	// 1. transfer(E e)：若当前存在一个正在等待获取的消费者线程，即立刻移交之；否则，会插入当前元素e到队列尾部，并且等待进入阻塞状态，到有消费者线程取走该元素。
	// 2. tryTransfer(E e)：若当前存在一个正在等待获取的消费者线程（使用take()或者poll()函数），使用该方法会即刻转移/传输对象元素e；若不存在，则返回false，并且不进入队列。这是一个不阻塞的操作。
	// 3. tryTransfer(E e, long timeout, TimeUnit
	// unit)：若当前存在一个正在等待获取的消费者线程，会立即传输给它;否则将插入元素e到队列尾部，并且等待被消费者线程获取消费掉；若在指定的时间内元素e无法被消费者线程获取，则返回false，同时该元素被移除。
	// 4. hasWaitingConsumer()：判断是否存在消费者线程。
	// 5. getWaitingConsumerCount()：获取所有等待获取元素的消费线程数量。
	// 6.size()：因为队列的异步特性，检测当前队列的元素个数需要逐一迭代，可能会得到一个不太准确的结果，尤其是在遍历时有可能队列发生更改。
	// 7.批量操作：类似于addAll，removeAll, retainAll, containsAll, equals, toArray等方法，API不能保证一定会立刻执行。因此，我们在使用过程中，不能有所期待，这是一个具有异步特性的队列。
	// 其实transfer方法在SynchronousQueue的实现中就已存在了,只是没有做为API暴露出来。SynchronousQueue有一个特性:它本身不存在容量,只能进行线程之间的元素传送。SynchronousQueue在执行offer操作时，如果没有其他线程执行poll，则直接返回false.线程之间元素传送正是通过transfer方法完成的。
	// 我们知道ThreadPoolExecutor调节线程的原则是：先调整到最小线程，最小线程用完后，他会将优先将任务放入缓存队列(offer(task)),等缓冲队列用完了，才会向最大线程数调节。这似乎与我们所理解的线程池模型有点不同。我们一般采用增加到最大线程后，才会放入缓冲队列中，以达到最大性能。
}
