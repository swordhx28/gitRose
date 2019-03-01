package cn.vbox.init;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.vbox.server.MydfsStorageServer;
public class StoreServerInit implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent event) {
	}
	public void contextInitialized(ServletContextEvent event) {
		try {
			// 9999监听端口
			int port=9999;
			// d:/data:保存文件目录
			String basepath= "d:/data";
			MydfsStorageServer server = new MydfsStorageServer(port,basepath);
			server.startup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}