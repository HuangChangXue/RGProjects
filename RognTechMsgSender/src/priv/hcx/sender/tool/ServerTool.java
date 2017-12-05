package priv.hcx.sender.tool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerTool {
	static Map<String, ServerSocketChannel> servers = new HashMap<String, ServerSocketChannel>();

	public static void startServer(String host, String port) {

		Thread th = new ServerThread(host, port);
		th.start();
	}

	public static void stopServer(String host, String port) {
		String key = host + "_" + port;
		if (servers.containsKey(key)) {
			try {
				servers.get(key).close();
				servers.remove(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static class ServerThread extends Thread {
		private String host, port;
		private int BUF_SIZE = 1 << 10;

		public ServerThread(String host, String port) {
			this.host = host;
			this.port = port;
		}

		private void handleAccept(SelectionKey key) {
			ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
			try {
				int port = ssChannel.socket().getLocalPort();
				String hostname = ssChannel.socket().getInetAddress().getHostAddress();
				String keys = hostname + "_" + port;
				SocketChannel sc = ssChannel.accept();
				if (sc == null)
					return;
				sc.configureBlocking(false);
				sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void handleRead(SelectionKey key) {
			ByteBuffer buf = (ByteBuffer) key.attachment();
			SocketChannel sc = (SocketChannel) key.channel();
			try {
				long len = sc.read(buf);

				buf.putChar('\n').putChar('\r');
				// System.out.println(new String(buf.));
				// buf.clear();

			} catch (IOException e) {
				key.cancel();
			}

			key.interestOps( SelectionKey.OP_WRITE);
		}

		private void handleWrite(SelectionKey key) {
			ByteBuffer buf = (ByteBuffer) key.attachment();
			buf.flip();
			SocketChannel sc = (SocketChannel) key.channel();
			try {
				sc.write(buf);
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			key.cancel();
//			buf.compact();
			
//			key.interestOps(SelectionKey.)
		}

		@Override
		public void run() {
			String key = host + "_" + port;
			try {
				Selector selector = null;
				ServerSocketChannel ssc = null;

				selector = Selector.open();
				ssc = ServerSocketChannel.open();
				servers.put(key, ssc);
				if (host == null || host.trim().length() <= 0) {
					InetSocketAddress sa = new InetSocketAddress(Integer.valueOf(port));
					ssc.socket().bind(sa);
				} else {
					InetSocketAddress sa = new InetSocketAddress(host, Integer.valueOf(port));
					ssc.socket().bind(sa);
				}

				ssc.configureBlocking(false);

				ssc.register(selector, SelectionKey.OP_ACCEPT);
				while (ssc.isOpen()) {

					if (selector.select(1000) > 0) {
						Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
						while (iter.hasNext()) {
							SelectionKey slk = iter.next();
							if (slk.isAcceptable()) {
								handleAccept(slk);
							}
							if (slk.isReadable()) {
								handleRead(slk);
							}
							if (slk.isWritable()) {
								handleWrite(slk);
							}
						}
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
