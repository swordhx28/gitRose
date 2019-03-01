package cn.vbox.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TrackerServer extends Observable {
	private static TrackerServer trackerServer = null;

	// 鍙岄噸閿佹鏌�
	public static TrackerServer startup(String host, int port) {
		if (trackerServer == null) {
			synchronized (Object.class) {
				if (trackerServer == null) {
					trackerServer = new TrackerServer(host, port);
				}
			}
		}
		return trackerServer;
	}

	private Stack<SocketProxy> pool = new Stack<SocketProxy>();
	private ThreadLocal<SocketProxy> threadLocal = new ThreadLocal<SocketProxy>();
	private Enhancer enhancer = new Enhancer();

	private TrackerServer(final String host, final int port) {
		// 琚�鐭ヨ�
		this.addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				try {
					for (int i = 0; i < 5; i++) {
						Socket socket = new Socket(host, port);
						byte[] buf = new byte[36];
						InputStream in = socket.getInputStream();
						int len = in.read(buf);
						String jsessionid = new String(buf, 0, len);
						SocketProxy connProxy = new SocketProxy();
						connProxy.setConn(socket);
						connProxy.jsessionid = jsessionid;
						pool.push(connProxy);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.setChanged();
		this.notifyObservers();
		// 灏嗙敓鎴愪竴涓猻ocket浠ｇ悊,涓嶈鐓х潃鏈湰涓讳箟鍘荤炕璇�
		enhancer.setSuperclass(Socket.class);
		enhancer.setCallback(new MethodInterceptor() {
			// 灏卞儚 filter涓�牱鍙互鎷︽埅socket鐨勬墍鏈夋柟娉�-->filter,鍔ㄦ�浠ｇ悊鐨勭殑鍖哄埆
			// filter鑳藉仛鍒扮殑鍔ㄦ�浠ｇ悊涔熻兘鍋氬埌,浣嗘槸鍔ㄦ�浠ｇ悊鑳藉仛鍒扮殑filter涓嶄竴瀹氳兘鍋氬埌
			public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				// threadLocal鍙幏鍙栧綋鍓嶇嚎绋嬩笅鐨剆ocket
				if (threadLocal.get() == null) {
					Q: while (true) {
						// 鐩戞帶鍒颁笉鏄┖
						synchronized (Object.class) {
							while (!pool.isEmpty()) {
								threadLocal.set(pool.pop());
								break Q;
							}
						}
						// 鐩戞帶鍒扮┖
						while (pool.isEmpty()) {
							setChanged();
							notifyObservers();
							break;
						}
					}
				}
				SocketProxy p = threadLocal.get();
				if (method.getName().equals("close")) {
					// 绾跨▼宸茬粡涓嶈浣跨敤
					p.setHandle(false);
					p.setIdle(0);// 琚娇鐢ㄨ繃鐨刬dle浠�寮�
					pool.push(p);
					return null;// 涓嶈鍏惰皟鐢ㄧ湡姝ｇ殑close鏂规硶
				} else {
					Socket conn = p.poll();
					p.setHandle(true);
					return method.invoke(conn, args);// 璋冪敤鍏剁湡姝ｇ殑socket鏂规硶getOutPutStream
				}
			}
		});
	}

	// 涓轰粈涔堝畾涔変竴涓唴閮ㄧ被,鍥犱负涓嶈兘浠巗ocket涓幏鍙�socket杩炴帴鏃堕棿锛宻ocket鏄惁鏄娇鐢ㄧ姸鎬�
	public class SocketProxy {
		private Socket conn;
		// 鍙戝憜鏃堕棿
		private int idle;
		// 鏈夋病鏈夎浣跨敤
		private boolean handle;
		private String jsessionid;

		public boolean isHandle() {
			return handle;
		}

		public void setHandle(boolean handle) {
			this.handle = handle;
		}

		public void setIdle(int idle) {
			this.idle = idle;
		}

		public void setConn(Socket conn) {
			this.conn = conn;
		}

		public Socket poll() {
			return conn;
		}

		public SocketProxy() {
			// 璁剧疆绌洪棽鏃堕棿锛屽鏋滅┖闂叉椂闂磋秴杩�绉掞紝鍒欏洖鏀�
			new Thread(new Runnable() {
				public void run() {
					try {
						while (true) {
							Thread.sleep(1000);
							idle += 1000;
							// 浼戞伅鏃堕棿>100绉�骞朵笖宸茬粡涓嶈浣跨敤
							if (idle > 1000 && handle == false) {
								synchronized (Object.class) {
									// if (pool.size() > 5) {
									OutputStream out = conn.getOutputStream();
									out.write("qt".getBytes());
									conn.getInputStream().close();
									conn.close();
									pool.remove(SocketProxy.this);
									System.out.println("client socket is close-->socket" + conn.hashCode());
									break;
									// }
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

	// 浠庣綉椤典笂鎻愪氦涓婁紶鏂囦欢鐨勮〃鍗曟暟鎹�璇ユ暟鎹槸浠ユ祦鐨勫舰寮忎紶閫掔殑
	public String upload(final InputStream in, final String stuffix) {
		try {
			FutureTask<String> t = new FutureTask<String>(new Callable<String>() {
				public String call() throws Exception {
					// 杩斿洖涓�釜浠ｇ悊鐨剆ocket
					Socket socket = (Socket) enhancer.create();
					// 鍥犱负DataOut/inStream浼氬皢浼犺緭鐨勬祦杩涜涔辩爜澶勭悊
					// DataOutputStream dataOut = new
					// DataOutputStream(socket.getOutputStream());
					// DataInputStream dataIn = new
					// DataInputStream(socket.getInputStream());
					// jdk11 jdk8(娓╂晠鑰岀煡鏂� 璐柊()
					OutputStream dataOut = socket.getOutputStream();
					InputStream dataIn = socket.getInputStream();
					// dataOut.writeUTF("upload");
					dataOut.write("up".getBytes());
					dataOut.write(stuffix.getBytes());
					System.out.println("client filesize:" + in.available());
					int v = in.available();
					// 鍙戦�鏂囦欢闀垮害,瑙嗛娴�
					dataOut.write((v >>> 24) & 0xFF);
					dataOut.write((v >>> 16) & 0xFF);
					dataOut.write((v >>> 8) & 0xFF);
					dataOut.write((v >>> 0) & 0xFF);
					// dataOut.write(in.available());//0-255鐨勬暟 1024*1024*3
					int len = 0;
					byte[] buf = new byte[1024 * 1024];
					while ((len = in.read(buf)) != -1) {
						dataOut.write(buf, 0, len);
						dataOut.flush();
					}
					// 鎺ユ敹鏈嶅姟绔繑鍥炵殑鏂囦欢璺緞
					buf = new byte[64];
					len = dataIn.read(buf);
					String vpath = new String(buf, 0, len);
					System.out.println(vpath);
					in.close();
					socket.close();
					return vpath;
				}
			});
			new Thread(t).start();
			return t.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	//100涓姹�00涓嚎绋�
	public IData receive(final String url) {
		try {
			FutureTask<IData> t=new FutureTask<IData>(new Callable<IData>() {
				public IData call() throws Exception {
					final IData data=new IData();
					Socket socket = (Socket) enhancer.create(); // 鍥犱负DataOut/inStream浼氬皢浼犺緭鐨勬祦杩涜涔辩爜澶勭悊
					OutputStream dataOut = socket.getOutputStream();
					InputStream dataIn = socket.getInputStream();
					dataOut.write("rv".getBytes());
					dataOut.write(url.getBytes());
					// 鎺ユ敹鏂囦欢闀垮害
					int ch1 = dataIn.read();
					int ch2 = dataIn.read();
					int ch3 = dataIn.read();
					int ch4 = dataIn.read();
					int filesize = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
					byte[] buf = new byte[65535];
					ByteArrayOutputStream o = new ByteArrayOutputStream();
					int sum = 0;
					do {
						int len = dataIn.read(buf); // 鎶婃暟鎹啓鍏ュ唴瀛�
						o.write(buf, 0, len);
						sum += len;
					} while (sum != filesize ? true : false);
					data.setBuf( o.toByteArray());
					data.setLen(filesize);
					dataOut.write("over".getBytes());
					o.close();
					socket.close();
					return data;
				}
			});
			//姣忎竴娆℃牴鎹畊rl鑾峰彇鏂囦欢,灏卞紑鍚竴涓嚎绋�
			new Thread(t).start();
			return t.get();//绛夊緟绾跨▼鎵ц杩斿洖IData鏁版嵁
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
