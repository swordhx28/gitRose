package Stack;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MainS {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				Scanner scanner = new Scanner(System.in);
				System.out.println("������������� ��");
				String data = scanner.next();
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:/io.txt"));
				out.write(data.getBytes());
				out.flush();
				out.close();
				scanner.close();
	}

}
