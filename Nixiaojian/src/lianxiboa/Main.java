package lianxiboa;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
	private static String[] box = new String[1];
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName()+"  ׼���������");
				rwl.readLock().lock();
				System.out.println(Thread.currentThread().getName()+"  �����������");
				if (box[0] == null) {	
					System.out.println(Thread.currentThread().getName()+"  ׼��ж�ض���");
					rwl.readLock().unlock();
					System.out.println(Thread.currentThread().getName()+"  ����ж�����");
					System.out.println(Thread.currentThread().getName()+"  ׼������д��");
					rwl.writeLock().lock();
					System.out.println(Thread.currentThread().getName()+"  д���������");
					if (box[0] == null) { // ��ֹ����̼߳������ݣ�ʹ��˫�˼�����
						box[0] = String.valueOf(Math.random());
					}
					rwl.writeLock().unlock();
					System.out.println(Thread.currentThread().getName()+"  д��ж�����");
					rwl.readLock().lock();
					System.out.println(Thread.currentThread().getName()+"  ����ж�����");
				}else {
					System.out.println(box[0]);
				}
				rwl.readLock().unlock();
				System.out.println(Thread.currentThread().getName()+"  д���������");
			}
		};
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
	}
}