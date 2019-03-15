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
					System.out.println("你打一个死锁程序给我看");
					synchronized (Main.class) {
					System.out.println("哇，真的不错啊");
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
					System.out.println("你给工作我，我就给你代码，这就是应该是一个死锁了");
					synchronized (String.class) {
						System.out.println("好了，我打代码给你看吧");
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
