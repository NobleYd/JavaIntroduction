package nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * 使用Buffer读写数据一般遵循以下四个步骤：<br/>
 * 1 写入数据到Buffer<br/>
 * 2 调用flip()方法<br/>
 * 3 从Buffer中读取数据<br/>
 * 4 调用clear()方法或者compact()方法<br/>
 * 当向buffer写入数据时，buffer会记录下写了多少数据。<br/>
 * 一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。<br/>
 * 在读模式下，可以读取之前写入到buffer的所有数据。<br/>
 * 一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。<br/>
 * 有两种方式能清空缓冲区：调用clear()或compact()方法。<br/>
 * clear()方法会清空整个缓冲区。<br/>
 * compact()方法只会清除已经读过的数据。<br/>
 * 任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。<br/>
 */
public class BasicTest {

	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		// create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf); // read into buffer.
		while (bytesRead != -1) {
			buf.flip(); // make buffer ready for read
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get()); // read 1 byte at a time
			}
			buf.clear(); // make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		aFile.close();

	}

	/***
	 * 工作原理
	 * 
	 * capacity position limit
	 * 
	 * position和limit的含义取决于Buffer处在读模式还是写模式。<br/>
	 * 不管Buffer处在什么模式，capacity的含义总是一样的。<br/>
	 * 
	 * position
	 * 
	 * 当你写数据到Buffer中时， position表示当前的位置。
	 * 初始的position值为0.当一个byte、long等数据写到Buffer后，position会向前移动到下一个可插入数据的Buffer单元。
	 * position最大可为capacity – 1.
	 * 
	 * 当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0.
	 * 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。 <br/>
	 * 
	 * limit
	 * 
	 * 在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
	 * 
	 * 当切换Buffer到读模式时，limit表示你最多能读到多少数据。
	 * 因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。
	 * 换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）
	 * 
	 */
}
