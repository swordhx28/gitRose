package cn.Nixiaojain.zoo;

import java.util.Scanner;

public class Long {
	public static void main(String[] args) throws Exception {
		System.out.println("������ȷ��ϲ���ı������:");
		Scanner scanner=new Scanner(System.in);
		while(scanner.hasNext()){
			System.out.print("��ϲ���ı��������:");
			String lang = scanner.next();
			if(lang.equals("C")){
				System.out.println("�Ҿ�ҪѧJAVA");
				scanner.close();
				break;
			}else{
				System.out.println("�Ҳ���ѧ��");
			}
			System.out.println(lang);
		}
	}
}
