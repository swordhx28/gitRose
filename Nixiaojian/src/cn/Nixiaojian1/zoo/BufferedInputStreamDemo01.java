package cn.Nixiaojian1.zoo;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BufferedInputStreamDemo01 {

	public static final int SIZE = 1024;

	public static void main(String[] args) {
		File f = null;
		InputStream input = null;
		BufferedInputStream bis = null;
		StringBuilder strBuild = null;
		SimpleDateFormat sdf = null;
		Date d = null;
		long start = 0L;
		long end = 0L;
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			strBuild = new StringBuilder();
			start = System.currentTimeMillis();
			d = new Date();
			if (d != null) {
				d.setTime(start);
			}
			System.out.println("程序开始执行时间:" + sdf.format(d));
			f = new File("d:" + File.separator + "readme.txt");
			input = new FileInputStream(f);
			//指定文件带缓冲区的读取流且指定缓冲区大小为2KB 
			bis = new BufferedInputStream(input, 2 * SIZE);
			int bisLength = bis.available();
			int readLength = 0;
			byte[] byteArray = new byte[SIZE];
			int tmp = 0;
			while ((tmp = bis.read(byteArray)) != -1) {
				strBuild.append(new String(byteArray, 0, tmp));
				System.out.println("每次读取字节数量:" + tmp);
				System.out.println("文件中剩余字节数:" + input.available());
			}
			System.out.println(String.format("文件的大小:%d,缓冲区读取流返回的大小:%d", f.length(), bisLength));
			System.out.println("文件的内容：" + strBuild.toString());
			System.out.println("字符串长度:" + strBuild.toString().length());
			char[] cTmp = strBuild.toString().toCharArray();
			System.out.println("字符串->字符数组长度:" + cTmp.length);
			end = System.currentTimeMillis();
			d = new Date();
			if (d != null) {
				d.setTime(end);
			}
			System.out.println("程序执行的结束时间:" + sdf.format(d));
			System.out.println("<-------------******************---------------->");
			System.out.println("程序执行时间(ms):" + (end - start) + "毫秒");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}