package Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mainke {

	public static void main(String[] args) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						ServerSocket server = new ServerSocket(9999);
						System.out.println("等待客户端连接:");
						ExecutorService pool = Executors.newFixedThreadPool(5);
						while (true) {
							Socket socket = server.accept();
							System.out.println("已连接："+socket.getRemoteSocketAddress());
							pool.execute(new Runnable() {
								@Override
								public void run() {
									try {
										InputStream in =socket.getInputStream();
										OutputStream out = socket.getOutputStream();
										DataOutputStream  dataout = new DataOutputStream(out);
										DataInputStream  datain = new DataInputStream(in);
										long filesize = datain.readLong();
										int time = datain.readInt();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
	}

}
