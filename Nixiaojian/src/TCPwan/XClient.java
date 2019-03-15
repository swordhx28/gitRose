package TCPwan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.print.DocFlavor.STRING;

public class XClient {
	public static void main(String[] args) {
		try {
			final Scanner scanner = new Scanner(System.in);
			System.out.print("请输入上传文件地址:");
			//String filename = "d:/usr/local/jdk-6u45-windows-x64.exe";
			String filename=scanner.next();
			final File file = new File(filename);
			if(!file.exists()){
				System.out.println("文件不存在!请检查文件路径是否正确!");
				return;
			}
			final long filesize = file.length();
			final int block = 1024 * 1024 * 10;
			final int times = (int)Math.ceil(filesize / (float) block);
			//计算出最后一块的文件大小 (1024*1024*10)x3+10
			final int endsize=(int)(filesize-((times-1)*block));
			final String fileNameId = UUID.randomUUID().toString();
			ExecutorService pool = Executors.newFixedThreadPool(3);
			for (int i = 0; i < times; i++) {
				final int j=i;
				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Socket socket = new Socket("localhost", 9999);
							OutputStream o = socket.getOutputStream();
							InputStream in = socket.getInputStream();
							DataInputStream datain = new DataInputStream(in);
							// 加密流
							DataOutputStream dataout = new DataOutputStream(o);
							// 截取文件后缀
							String stuffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);// /
							// 0.把文件名传输过去
							dataout.writeUTF(fileNameId + "-" + j);
							// 1.把文件后缀写到服务端 // pp.txt
							dataout.writeUTF(stuffix);
							// 2.把每次读的大小写到服务端,最后一节文件大小<=block
							int blocksize=(j==(times-1))?endsize:block;
							dataout.writeInt(blocksize);
							RandomAccessFile rFile = new RandomAccessFile(file, "r");
							rFile.seek(j * block);
							byte[] buf = new byte[block];
							int len = rFile.read(buf);
							// 我们每次发送到服务端的数据是block大小
							//问题:这边没有传输完成！也就是说因为网络的问题
							//该数据不是一次性，传输过去的，或者传输的时候是不均段传输
							dataout.write(buf, 0, len);
							dataout.flush();
							scanner.close();
							//必须等待服务器接收完毕，所有的流才关闭
							datain.readUTF();
							dataout.close();
							socket.close();
							rFile.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			pool.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
