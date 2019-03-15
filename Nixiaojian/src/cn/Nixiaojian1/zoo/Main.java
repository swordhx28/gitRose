package cn.Nixiaojian1.zoo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:/readme.txt"),2);
		in.mark(3);
		System.out.println((char)in.read());
		System.out.println((char)in.read());
		in.reset();
		System.out.println((char)in.read());
		System.out.println((char)in.read());
		in.close();
	}
}