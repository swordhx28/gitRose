package Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			public void run() {
				try {
					ServerSocket server = new ServerSocket(9999);
					System.out.println("等待客户端连接");
					ExecutorService pool = Executors.newFixedThreadPool(5);
					while (true) {
						Socket socket = server.accept();
						System.out.println("已连接:" + socket.getRemoteSocketAddress());
						pool.execute(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									InputStream in = socket.getInputStream();
									OutputStream out = socket.getOutputStream();
									DataOutputStream dataout = new DataOutputStream(out);
									DataInputStream dataIn = new DataInputStream(in);
									long filesize = dataIn.readLong();
									int times = dataIn.readInt();
									byte[] buf = new byte[1024 * 1024*3];
									for (int i = 0; i < times-1; i++) {
										String filename = UUID.randomUUID().toString();
										FileOutputStream fileout = new FileOutputStream("D:/" + filename + ".txt");
										
										int blocksize = dataIn.readInt();
										long sum = 0;
										int size = 0;
										do {
											size = dataIn.read(buf);
											fileout.write(buf, 0, size);

										} while ((sum += size) != blocksize ? true : false);
										fileout.close();
									}
									dataout.writeUTF("over");
									dataIn.close();
									out.close();
									in.close();
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}

							}
						});
						pool.shutdownNow();

					}
				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
		Thread.sleep(1000);
		new Thread(new Runnable() {
			public void run() {
				try {
					Socket socket = new Socket("127.0.0.1", 9999);
					OutputStream out = socket.getOutputStream();
					DataOutputStream dataOut = new DataOutputStream(out);
					InputStream in = socket.getInputStream();
					DataInputStream dataIn = new DataInputStream(in);
					Scanner scanner = new Scanner(System.in);
					System.out.print("请输入那你的路径:");
					String name = scanner.next();
					File file = new File(name);
					long length = file.length();
					dataOut.writeLong(length);
					if (file.exists()) {
						int block = 1024 * 1024 * 3;
						int time = (int) Math.ceil(length / (float) block);
						dataOut.writeInt(time);

						for (int i = 0; i < time;i++) {
							RandomAccessFile raf = new RandomAccessFile(file, "r");
							raf.seek(i * block);
							if (i == (time - 1)) {
								int x = (int) (length - ((time - 1) * block));
								dataOut.writeInt(x);
							} else {
								dataOut.writeInt(block);
							}
							byte[] buf = new byte[block];
							int size = raf.read(buf);
							dataOut.write(buf, 0, size);
							String data = dataIn.readUTF();
							if (data.equals("over")) {
								System.out.println("接收到服务端关闭信息！关闭当前客户端");
								scanner.close();
								raf.close();
								dataOut.close();
								dataIn.close();
								socket.close();
							} 
													
							}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}