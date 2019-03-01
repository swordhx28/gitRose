package cn.vbox.init;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.vbox.dao.DBUtil;
import cn.vbox.entity.Tadmin;
public class InitAdmin implements ServletContextListener{
	DBUtil dbUtil=new DBUtil();
	public void contextDestroyed(ServletContextEvent arg0) {
	}
        //��tomcat��������һ��admin
	public void contextInitialized(ServletContextEvent event) {
		//������һ���û���Ϊroot����Ϊroot�Ĺ���Ա�û�
		Tadmin admin = (Tadmin)dbUtil.findById(Tadmin.class, 1);
		if(admin==null){
		    admin=new Tadmin();
		    admin.setUsername("root");
		    admin.setPassword("hello");
		    dbUtil.save(admin);
		}
	}
}