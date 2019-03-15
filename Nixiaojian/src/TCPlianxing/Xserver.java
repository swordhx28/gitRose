package TCPlianxing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import TCPwan.Log;

public class Xserver {

	public static void main(String[] args) {
		try {
			final int readsize = 1024 * 1024;
			ServerSocket serverSocket = new ServerSocket(9999);
			ExecutorService pool = Executors.newFixedThreadPool(5);
			ResourceBundle bundle = ResourceBundle.getBundle("projcet");
			final String basepath = bundle.getString("basepth");
			new File(basepath).mkdirs();
			while (true) {
				final Socket Socket = serverSocket.accept();
				InputStream in = Socket.getInputStream();
				OutputStream out = Socket.getOutputStream();
				DataInputStream dataIn = new DataInputStream(in);
				DataOutputStream dataOu = new DataOutputStream(out);
				pool.execute(new Runnable() {

					@Override
					public void run() {
						try {
							String fileid = dataIn.readUTF();
							String sutffix = dataIn.readUTF();
							int block = dataIn.readInt();
							String fliename = basepath + fileid + "." + sutffix;
							FileOutputStream out = new FileOutputStream(fliename);
							byte[] buf = new byte[readsize];
							int boxsize = 0;
							do {
								int len = dataIn.read(buf);
								out.write(buf, 0, len);
								out.flush();
								boxsize += len;
								Log.info("file save path" + fliename);
							} while (boxsize != block);
						} catch (Exception e) {
							try {
								e.printStackTrace();
								e.printStackTrace(new PrintStream(Log.logpath));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				});
			}
		} catch (Exception e) {
			try {
				e.printStackTrace();
				e.printStackTrace(new PrintStream(Log.logpath));
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
