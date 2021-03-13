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
		// 1��ȡͨ��
		ServerSocketChannel server = ServerSocketChannel.open();
		
		// 2�л��ɷ�����ģʽ
		server.configureBlocking(false);

		// 3������
		server.bind(new InetSocketAddress(6666));
		
		// 4��ȡѡ����
		Selector selector = Selector.open();
		
		// 4.1��ͨ��ע�ᵽѡ�����ϣ�ָ�����ա�����ͨ�š��¼�
		server.register(selector, SelectionKey.OP_ACCEPT);
		
		// 5��ѯ��ǰѡ�������ѡ����������¼�--->ֻҪselect()>0��˵���Ѿ���
		while(selector.select() > 0) {
			// 6��ȡ��ǰѡ��������ע��ġ�ѡ��������Ѿ����ļ����¼���
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			
			// 7��ȡ�Ѿ��� ���¼�����ͬ���¼�����ͬ���£�
			while(iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				
				// �����¼�����
				if (selectionKey.isAcceptable()) {
					// 8��ȡ�ͻ��˵�����
					SocketChannel client = server.accept();
					
					// 8.1�л��ɷ�����״��
					client.configureBlocking(false);
					
					// 8.2ע�ᵽѡ������-->�õ��ͻ��˵�����Ϊ�˶�ȡͨ�������ݣ����������¼���
					client.register(selector, selectionKey.OP_READ);
					
				} else if(selectionKey.isReadable()) {// ���¼�����
					// 9��ȡ��ǰѡ����������״̬��ͨ��
					SocketChannel client = (SocketChannel) selectionKey.channel();
					
					// 9.1��ȡ����
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					
					// �õ��ļ�ͨ�������ͻ��˴��ݹ�����ͼƬд��������Ŀ�£�дģʽ��û�д�����
					FileChannel outChannel = FileChannel.open(Paths.get("D:\\1610504296(1).jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
					
					while(client.read(buffer) > 0) {
						// �ڶ�֮ǰ��Ҫ�л��ɶ�ģʽ
						buffer.flip();
						
						outChannel.write(buffer);
						
						// �����л���дģʽ�����ùܵ�������ȡ�ļ�������
						buffer.clear();
					}
				}
				// 10ȡ��ѡ������Ѿ���������¼�����Ӧ��ȡ�����ˣ�
				iterator.remove();
			}
		}
	}
}
