package taobao;

import java.util.Date;

import com.yueqian.cn.dao.IUserDao;
import com.yueqian.cn.dao.entity.User;
import com.yueqian.cn.dao.impl.StudentDao;
import com.yueqian.cn.dao.impl.StudentDaoImpl;
import com.yueqian.cn.dao.impl.UserDaoImpl;

public class TsetStudenDaoImpl {
	public static void main(String[] args) {
		/*StudentDao userDao = new StudentDaoImpl();
		User user = new User(null, 23, "��С��", "Ů", "ˮ��", new Date(), 321D);
		int rows = userDao.save(user);
		if (rows>0) {
			System.out.println("����ɹ���");
		}else {
			System.out.println("����ʧ�ܣ�");
		}*/
		
		
		
		/*StudentDao userDao = new StudentDaoImpl();
		User user = new User();
		user.setId(1);
		int rows = userDao.delete(user);
		if (rows>0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");

		}*/
		
		
		
		/*StudentDao userDao = new StudentDaoImpl();
		User user = new User(5, 12, "��С��", "Ů", "����", new Date(), 7561D);
		int rows = userDao.update(user);
		if (rows>0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");

		}*/
		
		
		
		StudentDao userDao = new StudentDaoImpl();
		User user = userDao.getById(2);
		System.out.println(user);
	}

	
}
