package taobao;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.yueqian.cn.dao.entity.User;


public class TestStudentException {
	public static void main(String[] args) {
		Configuration config= null;
		SessionFactory sf = null;
		Transaction tx= null;
		Session session=null;
		
		try {
			 config = new Configuration().configure("hibernateStudent.cfg.xml");
			 sf = config.buildSessionFactory();
			session = sf.openSession();
			 tx = session.beginTransaction();
			User user = new User(null, 22, "ÍõÐ¡¹·", "ÄÐ", "»ðÐÇ", new Date(), 321D);
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
