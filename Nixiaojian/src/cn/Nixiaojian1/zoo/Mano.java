package cn.Nixiaojian1.zoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mano {
	public static void main(String[] args) {
		String name = "D:/data/";
		new File(name).mkdirs();
		String box = "D:/readme.txt";
		String size = box.substring(box.lastIndexOf("."));
		int block = 10;
		long longsize = new File(box).length();
		int times = (int) Math.ceil(longsize / (float) block);
		ExecutorService pool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < times; i++) {
			final int j = i;
			pool.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						RandomAccessFile in = new RandomAccessFile(box, "r");
						in.seek(j * 10);
						byte[] buf = new byte[block];
						int threadsize = in.read(buf);
						String uuid = UUID.randomUUID().toString();
						FileOutputStream out = new FileOutputStream(name + uuid + size);
						out.write(buf, 0, threadsize);
						in.close();
						out.close();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			});
		}
		pool.shutdown();
	}
}
