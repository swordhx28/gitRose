package dontai;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class PoolSever extends Observable {
		private Stack<CoonProxy> pool =new Stack<CoonProxy>();
		private ThreadLocal<CoonProxy> threadLocal = new ThreadLocal<CoonProxy>();
		
		public PoolSever() {
			this.addObserver(new Observer() {
				public void update(Observable o, Object arg) {
					try {
						for (int i = 0; i < 5; i++) {
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/box", "root", "123");
							CoonProxy connProxy = new CoonProxy();
							connProxy.setCoon((CoonProxy) conn);
							pool.push(connProxy);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			this.setChanged();
			this.notifyObservers();
		}
		public class CoonProxy{
			private CoonProxy coon;
			private int idle;
			
			public void setIdle(int idle) {
				this.idle = idle;
			}
			public CoonProxy getCoon() {
				return coon;
			}
			public void setCoon(CoonProxy coon) {
				this.coon = coon;
			}
			
			public CoonProxy poll() {
				return coon;
			}
			
			public CoonProxy(){
				new Thread( new Runnable() {
					
					@Override
					public void run() {
						try {
							 while (true) {
								Thread.sleep(1000);
								idle+=1000;
								if (idle>10000) {
									synchronized (Object.class) {
										if (pool.size()>5) {
											coon.clone();
											pool.remove(CoonProxy.this);
											break;
										}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			
		}
		public Connection poll(){
			return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[] {Connection.class}, new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// TODO Auto-generated method stub
					if (threadLocal.get() == null) {
						Q:while (true) {
							synchronized (Object.class) {
								while (!pool.isEmpty()) {
									threadLocal.set(pool.pop());
									break;
								}
							}
							while (pool.isEmpty()) {
								setChanged();
									notifyObservers();
									break;
							}
						}
					}
					CoonProxy p= threadLocal.get();
					if (method.getName().equals("close")) {
						p.setIdle(0);
						pool.push(p);
						return null;
					}else {
						Connection conn= (Connection) p.poll();
						return method.invoke(conn, args);
					}
				}
			});
		}
		public static void main(String[] args) throws Exception {
			final PoolSever poolSever = new PoolSever();
			for (int i = 0; i < 12; i++) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Connection conn = poolSever.poll();
							System.out.println(conn);
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}).start();
			}
			System.in.read();
		}
		
}
