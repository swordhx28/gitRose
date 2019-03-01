package cn.vbox.server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.coobird.thumbnailator.Thumbnails;

public class MydfsStorageServer {
	private int port;
	private String basepath;

	public MydfsStorageServer(int port, String basepath) {
		this.port = port;
		// d:\\data d:/data/--->d:/data
		basepath = basepath.replace("\\", "/"); // d:/data
		basepath = basepath.endsWith("/") == true ? basepath.substring(0, basepath.lastIndexOf("/")) : basepath;
		this.basepath = basepath;
	}

	public void startup() {
		try {
			// 璇ユ湇鍔′細鏀惧湪tomcat鍚姩,鍥犱负server.accept();鏄竴涓樆濉炲紡鐨勬柟娉�浼氬鑷磘omcat鍚姩鐨勬椂鍊欓樆濉炰綇
			// 鎵�互鏀惧湪涓�釜绾跨▼涓惎鍔�tomcat鍚姩灏变笉浼氳闃诲浜�
			new Thread(new Runnable() {
				public void run() {
					Folder.initFolder(basepath);
					// 瑙ｈ劚
					ExecutorService threadPool = Executors.newFixedThreadPool(5);
					try {
						ServerSocket server = new ServerSocket(port);
						while (true) {
							final Socket socket = server.accept();
							threadPool.execute(new Runnable() {
								public void run() {
									try {
										// DataInputStream dataIn = new
										// DataInputStream(socket.getInputStream());
										// DataOutputStream dataOut = new
										// DataOutputStream(socket.getOutputStream());
										InputStream dataIn = socket.getInputStream();
										OutputStream dataOut = socket.getOutputStream();
										dataOut.write(UUID.randomUUID().toString().getBytes());
										// 涓�釜socket鍙互閲嶅鎺ユ敹鍜屼紶閫掓暟鎹�
										while (true) {
											byte[] buf = new byte[2];
											int len = dataIn.read(buf);
											String status = new String(buf, 0, len);// GBK
																					// GBK瀛楃闆嗗寘鎷珹SCII瀛楃闆�
											// String status = dataIn.readUTF();
											if (status.equals("up")) {
												buf = new byte[3];
												len = dataIn.read(buf);
												String stuffix = new String(buf, 0, len);
												int ch1 = dataIn.read();
												int ch2 = dataIn.read();
												int ch3 = dataIn.read();
												int ch4 = dataIn.read();
												int filesize = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
												System.out.println("server filesize:" + filesize);
												int sum = 0;
												// tcp/ip鍗忚瑙勫畾鍦╯ocket鏂囦欢浼犺緭杩囩▼涓�姣忔浼犻�鐨勬暟鎹湪0<data<=65535
												buf = new byte[65535];
												String filename = UUID.randomUUID().toString() + "-000-000" + "." + stuffix;
												String dir = filename.substring(0, 2) + "/" + filename.substring(2, 4) + "/";
												// 898021435689-79abcd
												FileOutputStream fileout = new FileOutputStream(basepath + "/" + dir + filename);
												do {
													len = dataIn.read(buf);
													fileout.write(buf, 0, len);
													sum += len;
													System.out.println(sum);
												} while (sum != filesize ? true : false);
												fileout.close();
												// M00/group/9a/0e/9a0e8999-a53a-44e6-a1b4-4edbf988d056-010-020.jpg
												// socket涓�浼犺緭鏈�ぇ65535 鏈�皬64涓瓧鑺�
												System.out.println(("m00/group/" + dir + filename));
												dataOut.write(("m00/group/" + dir + filename).getBytes());
												// 鍏堝叧闂湇鍔＄鐨勬祦

											} else if (status.equals("rv")) {
												buf = new byte[64];
												len = dataIn.read(buf);
												// 鑾峰彇璇锋眰鍥剧墖鐨勯摼鎺ュ湴鍧�
												String url = new String(buf, 0, len);
												url = url.replace("m00/group", "");
												// 濡傛灉鏄渶瑕佸浘鐗囩缉鏀鹃摼鎺ュ湴鍧�
												if (url.matches("/[a-z0-9]{2}/[a-z0-9]{2}/[a-z0-9-]{44}\\.jpg")) {
													String width = url.split("-")[5];
													String height = url.split("-")[6].split("\\.")[0];
													// 瑕佺敓鎴愮缉鐣ュ浘
													if (!width.equals("000") && !height.equals("000")) {
														width = width.startsWith("0") ? width.substring(1) : width;
														height = height.startsWith("0") ? height.substring(1) : height;
														String srcurl = url.replaceAll("(?<=-)[0-9]{3}-[0-9]{3}(?=\\.)", "000-000");
														url = basepath + url;
														if (!new File(url).exists()) {
															// 鑾峰彇鍘熷浘璺緞鍦板潃
															File src = new File(basepath + srcurl);
															Thumbnails.of(new File[] { src }).forceSize(Integer.parseInt(width), Integer.parseInt(height)).toFile(url);
														}
													} else {
														url = basepath + url;
														System.out.println(url);
													}
													FileInputStream fileIn = new FileInputStream(url);
													int v = fileIn.available();
													// 鍙戦�鏂囦欢闀垮害,瑙嗛娴�
													dataOut.write((v >>> 24) & 0xFF);
													dataOut.write((v >>> 16) & 0xFF);
													dataOut.write((v >>> 8) & 0xFF);
													dataOut.write((v >>> 0) & 0xFF);
													// 鎶婃枃浠舵祦杈撳嚭鍒板鎴风
													dataOut.write(Files.readAllBytes(Paths.get(url)));
													buf = new byte[4];
													len = dataIn.read(buf);
													String data = new String(buf, 0, len);
													if (data.equals("over")) {
														fileIn.close();
													}
												}
											} else if (status.equals("qt")) {
												dataIn.close();
												dataOut.close();
												socket.close();
												System.out.println("server socket is close-->socket" + socket.hashCode());
												break;
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
