package nixiaojian;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Mmrchu {

	public static void main(String[] args) throws Exception {
		ByteArrayOutputStream in = new ByteArrayOutputStream();
		in.write(97);
		in.write(98);
		in.write(99);
		System.out.println(in.toString());
		
		in.write("ABCDEFG".getBytes(),0,5);
		System.out.println(in);
		
		int size = in.size();
		System.out.println("×Ö·ûÓÐ£º"+size);
		 
		byte[] newbyte = in.toByteArray();
		System.out.println("" + new String(newbyte));
		
		in.reset();
		System.out.println("after reset-----"+in.size());
		
		in.write("ABCDEFG".getBytes("GBK"));
		System.out.println("GBK:" + in.toString());
		
		System.out.println("UTF-8:" + in.toString("UTF-16"));
	}

}
