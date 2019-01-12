package taobao;

import java.sql.Connection;
import java.util.Date;

import javax.xml.soap.Text;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mysql.fabric.xmlrpc.base.Data;
import com.yueqian.cn.dao.entity.User;


public class TestUserException {
	public static void main(String[] args) {
		Configuration config= null;
		SessionFactory sf = null;
		Transaction tx= null;
		Session session=null;
		
		try {
			 config = new Configuration().configure("hibernate.cfg.xml");
			 sf = config.buildSessionFactory();
			session = sf.openSession();
			 tx = session.beginTransaction();
			User user = new User(null, 32, "ÍõÐ¡¹·", "ÄÐ", "»ðÐÇ", new Date(), 321D);
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tx.rollback();
		}finally {
			session.close(); 
		}
		
		
	}
}
