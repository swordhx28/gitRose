package lianxiboa;

public class Gongchang {
		private static Worke[] box = new Worke[1];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
									box[0] = new Worke();
									Object.class.notify();
								} catch (Exception e) {
									// TODO: handle exception
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
									box[0] = null; 
									Object.class.notify();
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
						}
					}
				}).start();
	}

}
