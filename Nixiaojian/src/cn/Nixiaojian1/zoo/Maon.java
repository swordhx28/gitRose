package cn.Nixiaojian1.zoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Maon {

	public static void main(String[] args) {
		try {
			final String name = "D:/readme.txt";
			long size = new File(name).length();
			System.out.println(size);
			final int blokck = 10;
			final int threadsize = (int)Math.ceil(size/(float)blokck);
			System.out.println(threadsize);
			ExecutorService pool = Executors.newFixedThreadPool(threadsize);
			for(int i=0;i<threadsize;i++) {
				final int j=i;
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							RandomAccessFile ras = new RandomAccessFile(name,"rw" );
							String uuidname = UUID.randomUUID().toString();
							File file = new File("D:/data");
							file.mkdir();
							FileOutputStream fileOut=new FileOutputStream(file.getAbsolutePath()+"/"+uuidname+"-"+j+".txt");
							int pos = j*10;
							ras.seek(pos);
							byte[] buf = new byte[10];
							int len=0;
							while ((len=ras.read(buf))!=-1) {
								fileOut.write(buf,0,len);
								break;
							}
							fileOut.close();
							ras.close();
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
