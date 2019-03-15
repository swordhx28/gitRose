package cn.Nixiaojain.zoo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static void main(String[] args) {
	Runnable runnable = new Runnable() {
		ReentrantLock lock = new ReentrantLock();
		public void run() {
			try {
				if(lock.tryLock(1,TimeUnit.SECONDS)) {
					System.out.println(Thread.currentThread().getName()+"执行中");
					Thread.sleep(2000);
				}else {
					System.out.println(Thread.currentThread().getName()+"获取锁失败");
				}
				
			} catch (Exception e) {
						e.printStackTrace();
			}finally {
				if (lock.isHeldByCurrentThread()) {
					System.out.println(Thread.currentThread().getName()+"执行完毕");
					lock.unlock();
				}
			}
		}
	};
	Thread t1 = new Thread(runnable);
	t1.setName("t1");
	Thread t2 = new Thread(runnable);
	t2.setName("t2");
	t1.start();
	t2.start();
}
	}