package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {

	public static void main(String[] args) throws IOException {

		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.socket().bind(new InetSocketAddress(1314));
		ByteBuffer buf = ByteBuffer.allocate(64);
		while (true) {
			buf.clear();

			SocketChannel sc = ssChannel.accept();

			int byteNumber;
			while (-1 != (byteNumber = sc.read(buf))) {
				buf.flip();
				System.out.print("receive: ");
				while (buf.hasRemaining()) {
					// System.out.print((char) (buf.get()));
					System.out.print(buf.getChar());
				}
				System.out.println();
				buf.clear();
			}
			if (byteNumber == -1) {
				System.out.println("Reached end-of-stream.");
			}
		}

	}

}
