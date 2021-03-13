package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class NoBlockClient {
	public static void main(String[] args) throws IOException {
		// 1获取通道
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",6666));
		
		// 1.1切换成非阻塞模式
		socketChannel.configureBlocking(false);
		
		// 1.2获取选择器
		Selector selector = Selector.open();
		
		// 1.3将通道注册到选择器中，获取服务端返回的数据
		socketChannel.register(selector, SelectionKey.OP_READ);
		
		// 2 发送一张图片给服务端
		FileChannel fileChannel = FileChannel.open(Paths.get("D:\\1610504296(1).jpg"), StandardOpenOption.READ);
		
		// 3.要使用NIO，有了Channel，就必然要有Buffer，Buffer是与数据打交道的
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		// 4读取本地文件，发送到服务器
		while(fileChannel.read(buffer) != -1) {
			// 在读之前切换成读模式
			buffer.flip();
			
			socketChannel.write(buffer);
			
			// 读完切换成写模式，能让管道继续读取文件的数据
			buffer.clear();
		}
		
		// 5轮询地获取选择器上已就绪的事件---> 只要select()>0 说明已就绪
		while(selector.select()>0) {
			// 6获取当前选择器所有注册的选择键（已就绪的监听事件）
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			
			// 7获取已就绪的事件（不同事件做不同的事情）
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				
				// 读事件就绪
				if (selectionKey.isReadable()) {
					// 8.1得到对应的通道
					SocketChannel channel = (SocketChannel)selectionKey.channel();
					
					ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
					
					// 9直到服务端要返回响应的数据给客户端，客户端在这里接收
					int readBytes = channel.read(responseBuffer);
					
					if(readBytes > 0) {
						// 切换读模式
						responseBuffer.flip();
						System.out.println(new String(responseBuffer.array(),0, readBytes));
					}
				}
				// 10取消选择键（已经处理过的事件，取消掉）
				iterator.remove();
			}
		}
	}
}
