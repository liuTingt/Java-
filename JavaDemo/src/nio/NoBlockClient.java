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
		// 1��ȡͨ��
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",6666));
		
		// 1.1�л��ɷ�����ģʽ
		socketChannel.configureBlocking(false);
		
		// 1.2��ȡѡ����
		Selector selector = Selector.open();
		
		// 1.3��ͨ��ע�ᵽѡ�����У���ȡ����˷��ص�����
		socketChannel.register(selector, SelectionKey.OP_READ);
		
		// 2 ����һ��ͼƬ�������
		FileChannel fileChannel = FileChannel.open(Paths.get("D:\\1610504296(1).jpg"), StandardOpenOption.READ);
		
		// 3.Ҫʹ��NIO������Channel���ͱ�ȻҪ��Buffer��Buffer�������ݴ򽻵���
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		// 4��ȡ�����ļ������͵�������
		while(fileChannel.read(buffer) != -1) {
			// �ڶ�֮ǰ�л��ɶ�ģʽ
			buffer.flip();
			
			socketChannel.write(buffer);
			
			// �����л���дģʽ�����ùܵ�������ȡ�ļ�������
			buffer.clear();
		}
		
		// 5��ѯ�ػ�ȡѡ�������Ѿ������¼�---> ֻҪselect()>0 ˵���Ѿ���
		while(selector.select()>0) {
			// 6��ȡ��ǰѡ��������ע���ѡ������Ѿ����ļ����¼���
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			
			// 7��ȡ�Ѿ������¼�����ͬ�¼�����ͬ�����飩
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				
				// ���¼�����
				if (selectionKey.isReadable()) {
					// 8.1�õ���Ӧ��ͨ��
					SocketChannel channel = (SocketChannel)selectionKey.channel();
					
					ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
					
					// 9ֱ�������Ҫ������Ӧ�����ݸ��ͻ��ˣ��ͻ������������
					int readBytes = channel.read(responseBuffer);
					
					if(readBytes > 0) {
						// �л���ģʽ
						responseBuffer.flip();
						System.out.println(new String(responseBuffer.array(),0, readBytes));
					}
				}
				// 10ȡ��ѡ������Ѿ���������¼���ȡ������
				iterator.remove();
			}
		}
	}
}
