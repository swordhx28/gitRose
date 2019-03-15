package TCPwan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//5G协议
public class XServer {
	public static void main(String[] args) {
		try {
			// 127.0.0.1:9999/192.168.3.20:9999
			final int readsize=1024*1024;
			//7x24*365 
			ServerSocket serverSocket = new ServerSocket(9999);
			ExecutorService pool = Executors.newFixedThreadPool(5);
			ResourceBundle bundle = ResourceBundle.getBundle("projcet");
			final String basepath = bundle.getString("basepath");
			new File(basepath).mkdirs();
			while (true) {
				final Socket socket = serverSocket.accept();// 等着链接
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				final DataOutputStream dataout=new DataOutputStream(out);
				final DataInputStream datain = new DataInputStream(in);
				pool.execute(new Runnable() {

					@Override
					public void run() {
						try {
							
							// 0.读到文件名
							String fileId = datain.readUTF();
							// 1.读到文件后缀
							String stuffix = datain.readUTF();
							// 2.读到每次读入大小
							int block = datain.readInt();
							// 拼接文件名字
							String fileName = basepath + fileId + "." + stuffix;
							FileOutputStream out = new FileOutputStream(fileName);
							byte[] buf = new byte[readsize];
							int boxsize=0;
							do {
								int len = datain.read(buf);//10 2 100
								out.write(buf, 0, len);
								out.flush();
								boxsize+=len;
							} while (boxsize!=block);
							out.close();
							dataout.writeUTF("over");
							datain.close();
							socket.close();
							dataout.close();
							Log.info("file save path:" + fileName);

						} catch (Exception e) {
							//没有日志不处理:约定优于配置(法律)
							try {
								e.printStackTrace();
								e.printStackTrace(new PrintStream(Log.logpath));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							//System.out默认打印在控制台
							Log.error(e.getMessage());
							//自己写代码都写不了 自己写的代码自己维护不了 能自己维护自己的代码-->自己才懂
							//要去维护别人的代码
						}

					}
				});

			}

		} catch (Exception e) {
			try {
				e.printStackTrace();
				e.printStackTrace(new PrintStream(Log.logpath));
				Log.error(e.getMessage());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}

	}
}
