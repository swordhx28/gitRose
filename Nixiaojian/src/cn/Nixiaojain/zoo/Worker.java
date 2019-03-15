package cn.Nixiaojain.zoo;
public class Worker {
	public void doWork() {
		try {
			System.out.print("编号:" + hashCode() + "工人:正在干活 ");
			int wtime = ((int) (Math.random() * 1000));
			Thread.sleep(wtime);
			System.out.println("工作时长:" + wtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}