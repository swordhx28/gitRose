package lianxiboa;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
	private static String[] box = new String[1];
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName()+"  准备载入读锁");
				rwl.readLock().lock();
				System.out.println(Thread.currentThread().getName()+"  读锁加载完毕");
				if (box[0] == null) {	
					System.out.println(Thread.currentThread().getName()+"  准备卸载读锁");
					rwl.readLock().unlock();
					System.out.println(Thread.currentThread().getName()+"  读锁卸载完毕");
					System.out.println(Thread.currentThread().getName()+"  准备载入写锁");
					rwl.writeLock().lock();
					System.out.println(Thread.currentThread().getName()+"  写锁载入完毕");
					if (box[0] == null) { // 防止后边线程加载数据，使用双端检测机制
						box[0] = String.valueOf(Math.random());
					}
					rwl.writeLock().unlock();
					System.out.println(Thread.currentThread().getName()+"  写锁卸载完毕");
					rwl.readLock().lock();
					System.out.println(Thread.currentThread().getName()+"  读锁卸载完毕");
				}else {
					System.out.println(box[0]);
				}
				rwl.readLock().unlock();
				System.out.println(Thread.currentThread().getName()+"  写锁载入完毕");
			}
		};
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
	}
}