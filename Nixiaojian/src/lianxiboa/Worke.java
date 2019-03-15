package lianxiboa;

public class Worke {
			public void doWork() {
				try {
					System.out.print("编号："+ hashCode() + "工人：正在干活");
					int in = ((int)(Math.random()* 5000));
					Thread.sleep(in);
					System.out.println("工作时长："+ in);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
}
