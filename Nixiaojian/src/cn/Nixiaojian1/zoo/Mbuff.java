package cn.Nixiaojian1.zoo;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Mbuff {
		public static void main(String[] args) throws Exception {
			Scanner scanner = new Scanner(System.in);
			System.out.println("«Î ‰»Î ˝æ›£∫");
			String data = scanner.next();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:/io.txt"),5);
			out.write(data.getBytes());
			out.flush();
			scanner.next();
			out.close();
			scanner.close();
		}
}
