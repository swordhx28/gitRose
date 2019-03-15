package cn.Nixiaojain.zoo;
public class Msan{
		public static void main(String[] args) {
			new Thread(new Runnable() {
				public void run() {
					try {
					System.out.println("的打士大夫");
					synchronized(String.class){
					System.out.println("dsaaasdf");
					Thread.sleep(1000);
					synchronized(Msan.class){
					System.out.println("jsdajlk");
					}
					}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
						try {
							System.out.println("dsa");
							synchronized(Msan.class) {
							System.out.println("dsaf");
							Thread.sleep(1000);
							synchronized(String.class){
							System.out.println("sadfas");
							}
							}
						}catch(Exception e) {
							e.printStackTrace();
						}
				}
				
			}).start();
		}

}