package lianxiboa;

public class Worke {
			public void doWork() {
				try {
					System.out.print("��ţ�"+ hashCode() + "���ˣ����ڸɻ�");
					int in = ((int)(Math.random()* 5000));
					Thread.sleep(in);
					System.out.println("����ʱ����"+ in);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
}
