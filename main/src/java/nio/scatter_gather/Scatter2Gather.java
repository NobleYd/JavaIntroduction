package nio.scatter_gather;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Scatter2Gather {

	public static void main(String[] args) throws IOException {
		// scatter指分散读取
		test1();
		// 对比普通情况
		// test2();
		// gather指聚集写入
		test3();
	}

	public static void test1() throws IOException {
		RandomAccessFile file = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = file.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(48);
		ByteBuffer buf2 = ByteBuffer.allocate(48);

		long bytesRead = inChannel.read(new ByteBuffer[] { buf, buf2 });
		while (bytesRead != -1) {
			System.out.println(System.lineSeparator() + "读取到" + bytesRead + "字节的数据。");
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			buf2.flip();
			while (buf2.hasRemaining()) {
				System.out.print((char) buf2.get());
			}

			buf.clear();
			buf2.clear();

			bytesRead = inChannel.read(new ByteBuffer[] { buf, buf2 });
		}
		file.close();

	}

	public static void test2() throws IOException {
		RandomAccessFile file = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = file.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			System.out.println(System.lineSeparator() + "读取到" + bytesRead + "字节的数据。");
			buf.flip();

			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}

			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		file.close();

	}

	public static void test3() throws IOException {
		RandomAccessFile file = new RandomAccessFile("data/nio-data-new.txt", "rw");
		FileChannel inChannel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.put("Hello ".getBytes());
		buf.put("World".getBytes());
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		buf2.put(". I am yidan.".getBytes());

		buf.flip();
		buf2.flip();
		//注意不调用flip，相当于就是写入position到limit部分。
		long bytesWrite = inChannel.write(new ByteBuffer[] { buf, buf2 });

		System.out.println(System.lineSeparator() + "写入" + bytesWrite + "字节");

		file.close();

	}

}
