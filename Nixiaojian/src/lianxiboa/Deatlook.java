package lianxiboa;

import cn.Nixiaojain.zoo.Main;

public class Deatlook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("���һ������������ҿ�");
					synchronized (Main.class) {
					System.out.println("�ۣ���Ĳ���");
					Thread.sleep(1000);
					synchronized (String.class) {
						System.out.println("hehe");
					}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("��������ң��Ҿ͸�����룬�����Ӧ����һ��������");
					synchronized (String.class) {
						System.out.println("���ˣ��Ҵ������㿴��");
						Thread.sleep(1000);
						synchronized (Main.class) {
							System.out.println("xixi");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}

}
