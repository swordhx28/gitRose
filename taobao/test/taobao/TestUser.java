package taobao;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yueqian.cn.dao.entity.User;


public class TestUser {
	public static void main(String[] args) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		Session session =sf.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User(null, 32, "ÍõÐ¡¹·", "ÄÐ", "»ðÐÇ", new Date(), 321D);
		session.save(user);
		tx.commit();
		session.close(); 
		
	}
}
