package TCPwan;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Scanner scanner=new Scanner(System.in);
				System.out.print("�������û���:");
				String name=scanner.next();
				System.out.println(name);
				scanner.close();
				
			}
		}).start();
	}
}
