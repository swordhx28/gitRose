package cn.Nixiaojain.zoo;
public class DeathLockMain {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("Lock1 running");
					synchronized (String.class) {
						System.out.println("Lock1 lock obj1");
						Thread.sleep(3000);
						synchronized (DeathLockMain.class) {
							System.out.println("Lock1 lock obj2");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();;
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("Lock2 running");
					synchronized (DeathLockMain.class) {
						System.out.println("Lock2 lock obj2");
						Thread.sleep(3000);
						synchronized (String.class) {
							System.out.println("Lock2 lock obj1");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}