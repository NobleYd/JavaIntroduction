package nio.transfer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferFrom {
	public static void main(String[] args) throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("data/fromFile.txt", "rw");
		RandomAccessFile toFile = new RandomAccessFile("data/toFile.txt", "rw");

		FileChannel fromChannel = fromFile.getChannel();
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();
		
		System.out.println("FromChannel.size() is " + count);
		
		toChannel.transferFrom(fromChannel, position, count);

		fromFile.close();
		toFile.close();

		fromChannel.close();
		toChannel.close();

	}
}
