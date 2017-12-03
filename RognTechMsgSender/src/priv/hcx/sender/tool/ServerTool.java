package priv.hcx.sender.tool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Map;

public class ServerTool {
	static Map<String, ServerSocket> servers = new HashMap<String, ServerSocket>();

	public static void startServer(String host, String port) {
		
		Thread th=new ServerThread(host,port);
		th.start();
	}

	public static void stopServer(String host, String port) {
		String key = host + "_" + port;
		if (servers.containsKey(key)) {
			try {
				servers.get(key).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static class ServerThread extends Thread {
		private String host, port;

		public ServerThread(String host, String port) {
			this.host = host;
			this.port = port;
		}

		@Override
		public void run() {
			String key = host + "_" + port;
			try {
				ServerSocket so = new ServerSocket();
				if (host == null || host.trim().length() <= 0) {
					InetSocketAddress sa = new InetSocketAddress(Integer.valueOf(port));
					so.bind(sa);
				} else {
					InetSocketAddress sa = new InetSocketAddress(host, Integer.valueOf(port));
					so.bind(sa);
				}
				ServerSocketChannel ssc=so.getChannel();
				ssc.configureBlocking(false);
				Selector ssl=Selector.open();
				ssc.register(ssl, SelectionKey.OP_ACCEPT);
//				Selector sl=new Selector();
				servers.put(key, so);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}	
}
