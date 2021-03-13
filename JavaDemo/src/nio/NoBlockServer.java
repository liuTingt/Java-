package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;

public class NoBlockServer {
	public static void main(String[] args) throws IOException {
		// 1获取通道
		ServerSocketChannel server = ServerSocketChannel.open();
		
		// 2切换成非阻塞模式
		server.configureBlocking(false);

		// 3绑定连接
		server.bind(new InetSocketAddress(6666));
		
		// 4获取选择器
		Selector selector = Selector.open();
		
		// 4.1将通道注册到选择器上，指定接收“监听通信”事件
		server.register(selector, SelectionKey.OP_ACCEPT);
		
		// 5轮询当前选择器上已“就绪”的事件--->只要select()>0，说明已就绪
		while(selector.select() > 0) {
			// 6获取当前选择器所有注册的“选择键”（已就绪的监听事件）
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			
			// 7获取已就绪 的事件（不同的事件做不同的事）
			while(iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				
				// 接收事件就绪
				if (selectionKey.isAcceptable()) {
					// 8获取客户端的连接
					SocketChannel client = server.accept();
					
					// 8.1切换成非阻塞状体
					client.configureBlocking(false);
					
					// 8.2注册到选择器上-->拿到客户端的连接为了读取通道的数据（监听就绪事件）
					client.register(selector, selectionKey.OP_READ);
					
				} else if(selectionKey.isReadable()) {// 读事件就绪
					// 9获取当前选择器读就绪状态的通道
					SocketChannel client = (SocketChannel) selectionKey.channel();
					
					// 9.1读取数据
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					
					// 得到文件通道，将客户端传递过来的图片写道本地项目下（写模式，没有创建）
					FileChannel outChannel = FileChannel.open(Paths.get("D:\\1610504296(1).jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
					
					while(client.read(buffer) > 0) {
						// 在读之前都要切换成读模式
						buffer.flip();
						
						outChannel.write(buffer);
						
						// 读完切换成写模式，能让管道继续读取文件的数据
						buffer.clear();
					}
				}
				// 10取消选择键（已经处理过的事件，就应该取消掉了）
				iterator.remove();
			}
		}
	}
}
