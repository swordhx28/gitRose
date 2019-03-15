package cn.Nixiaojain.zoo;

import java.util.Scanner;

public class Long {
	public static void main(String[] args) throws Exception {
		System.out.println("输入你确认喜欢的编程语言:");
		Scanner scanner=new Scanner(System.in);
		while(scanner.hasNext()){
			System.out.print("你喜欢的编程语言是:");
			String lang = scanner.next();
			if(lang.equals("C")){
				System.out.println("我就要学JAVA");
				scanner.close();
				break;
			}else{
				System.out.println("我不想学了");
			}
			System.out.println(lang);
		}
	}
}
