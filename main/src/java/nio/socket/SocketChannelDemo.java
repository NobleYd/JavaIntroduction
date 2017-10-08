package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class SocketChannelDemo {

	public static void main(String[] args) throws IOException {

		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("localhost", 1314));

		Scanner scanner = new Scanner(System.in);
		ByteBuffer buf = ByteBuffer.allocate(512);

		while (scanner.hasNext()) {
			String cmd = scanner.nextLine();
			if ("end".equals(cmd)) {
				System.out.println("退出...");
				sc.close();
				break;
			} else {
				buf.put(cmd.getBytes());
				buf.flip();
				System.out.println("开始写出...");
				while (buf.hasRemaining()) {
					int writeNumber = sc.write(buf);
					System.out.println("写出" + writeNumber + "字节...");
				}
				System.out.println("写出结束...");
				buf.clear();
			}
		}
		scanner.close();
	}

}
