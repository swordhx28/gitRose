package cn.Nixiaojain.zoo;
public class Worker {
	public void doWork() {
		try {
			System.out.print("���:" + hashCode() + "����:���ڸɻ� ");
			int wtime = ((int) (Math.random() * 1000));
			Thread.sleep(wtime);
			System.out.println("����ʱ��:" + wtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}