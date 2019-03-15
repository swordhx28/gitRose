package cn.Nixiaojain.zoo;

public class Factory {
	private static Worker[] box = new Worker[1];

	public static void main(String[] args) {
		// 创建工人
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					synchronized (Object.class) {
						try {
							if (box[0]!=null) {
								Object.class.wait();
							}
							 box[0]=new Worker();
							 Object.class.notify();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					synchronized (Object.class) {
						try {
							if (box[0]==null) {
								Object.class.wait();
							}
							 box[0].doWork();
							 box[0]=null;
							 Object.class.notify();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}