package cn.vbox.init;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.vbox.dao.DBUtil;
import cn.vbox.entity.Tadmin;
public class InitAdmin implements ServletContextListener{
	DBUtil dbUtil=new DBUtil();
	public void contextDestroyed(ServletContextEvent arg0) {
	}
        //当tomcat启动创建一个admin
	public void contextInitialized(ServletContextEvent event) {
		//创建第一个用户名为root密码为root的管理员用户
		Tadmin admin = (Tadmin)dbUtil.findById(Tadmin.class, 1);
		if(admin==null){
		    admin=new Tadmin();
		    admin.setUsername("root");
		    admin.setPassword("hello");
		    dbUtil.save(admin);
		}
	}
}