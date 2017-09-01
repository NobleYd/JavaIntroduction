package nio.selector;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Register {

	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		SelectableChannel channel = null;
		// channel.register(selector, SelectionKey.OP_READ);
		SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

		// 也可以单独设置
		// selectionKey.interestOps(SelectionKey.OP_READ);
		//
		// SelectionKey.OP_ACCEPT
		// SelectionKey.OP_CONNECT
		// SelectionKey.OP_READ
		// SelectionKey.OP_WRITE

		// 通道触发了一个事件意思是该事件已经就绪。
		// 所以，某个channel成功连接到另一个服务器称为“连接就绪”。
		// 一个server socket channel准备好接收新进入的连接称为“接收就绪”。
		// 一个有数据可读的通道可以说是“读就绪”。
		// 等待写数据的通道可以说是“写就绪”。

		// 可以通过返回的selectionKey获取interests
		int interestOps = selectionKey.interestOps();

		// 如果要判断返回的options集合，可以如下操作：

		boolean isInterestedInAccept = SelectionKey.OP_ACCEPT == (SelectionKey.OP_ACCEPT & interestOps);
		boolean isInterestedInConnect = SelectionKey.OP_CONNECT == (SelectionKey.OP_CONNECT & interestOps);
		boolean isInterestedInRead = SelectionKey.OP_READ == (SelectionKey.OP_READ & interestOps);
		boolean isInterestedInWrite = SelectionKey.OP_WRITE == (SelectionKey.OP_WRITE & interestOps);

		System.out.println("isInterestedInAccept = " + isInterestedInAccept);
		System.out.println("isInterestedInConnect = " + isInterestedInConnect);
		System.out.println("isInterestedInRead = " + isInterestedInRead);
		System.out.println("isInterestedInWrite = " + isInterestedInWrite);

		// 通过selectionKey访问channel和selector
		selectionKey.channel();
		selectionKey.selector();

		// ready 集合是通道已经准备就绪的操作的集合。
		selectionKey.isConnectable();
		selectionKey.isAcceptable();
		selectionKey.isReadable();
		selectionKey.isWritable();

		// 附加对象
		selectionKey.attach(new Object());
		selectionKey.attachment();

		// 选择通道
		selector.select();
		// select()阻塞到至少有一个通道在你注册的事件上就绪了。
		// select(long timeout)和select()一样，除了最长会阻塞timeout毫秒(参数)。
		// selectNow()不会阻塞，不管什么通道就绪都立刻返回

		// 一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了
		// 然后可以通过调用selector的selectedKeys()方法
		// 访问“已选择键集（selected key set）”中的就绪通道。
		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
		while (keyIterator.hasNext()) {
			SelectionKey key = keyIterator.next();
			if (key.isAcceptable()) {
				// a connection was accepted by a ServerSocketChannel.
			} else if (key.isConnectable()) {
				// a connection was established with a remote server.
			} else if (key.isReadable()) {
				// a channel is ready for reading
			} else if (key.isWritable()) {
				// a channel is ready for writing
			}
			keyIterator.remove();
		}

		// wakeUp()
		// 某个线程调用select()方法后阻塞了，即使没有通道已经就绪，也有办法让其从select()方法返回。只要让其它线程在第一个线程调用select()方法的那个对象上调用Selector.wakeup()方法即可。阻塞在select()方法上的线程会立马返回。
		// 如果有其它线程调用了wakeup()方法，但当前没有线程阻塞在select()方法上，下个调用select()方法的线程会立即“醒来（wake
		// up）”。

		// close
		// 用完Selector后调用其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭。
	}

}
