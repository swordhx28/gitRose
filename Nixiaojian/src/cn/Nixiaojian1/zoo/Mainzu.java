package cn.Nixiaojian1.zoo;
import java.io.FileInputStream;
public class Mainzu {
	public static void main(String[] args) throws Exception {
		FileInputStream lse=new FileInputStream("D:/readme.txt");
		lse.skip(3);
		int read = 0;
		while ((read=lse.read())!=-1) {
			System.out.print((char)read);
		}
		lse.close();
	}
}