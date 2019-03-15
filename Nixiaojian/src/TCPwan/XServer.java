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
//5GЭ��
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
				final Socket socket = serverSocket.accept();// ��������
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				final DataOutputStream dataout=new DataOutputStream(out);
				final DataInputStream datain = new DataInputStream(in);
				pool.execute(new Runnable() {

					@Override
					public void run() {
						try {
							
							// 0.�����ļ���
							String fileId = datain.readUTF();
							// 1.�����ļ���׺
							String stuffix = datain.readUTF();
							// 2.����ÿ�ζ����С
							int block = datain.readInt();
							// ƴ���ļ�����
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
							//û����־������:Լ����������(����)
							try {
								e.printStackTrace();
								e.printStackTrace(new PrintStream(Log.logpath));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							//System.outĬ�ϴ�ӡ�ڿ���̨
							Log.error(e.getMessage());
							//�Լ�д���붼д���� �Լ�д�Ĵ����Լ�ά������ ���Լ�ά���Լ��Ĵ���-->�Լ��Ŷ�
							//Ҫȥά�����˵Ĵ���
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
