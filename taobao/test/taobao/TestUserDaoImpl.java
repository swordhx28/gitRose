package taobao;

import java.util.Date;

import com.yueqian.cn.dao.IUserDao;
import com.yueqian.cn.dao.entity.User;
import com.yueqian.cn.dao.impl.UserDaoImpl;

public class TestUserDaoImpl {
	public static void main(String[] args) {
		/*IUserDao userDao = new UserDaoImpl();
		User user = new User(null, 23, "��С��", "Ů", "ˮ��", new Date(), 321D);
		int rows = userDao.save(user);
		if (rows>0) {
			System.out.println("����ɹ���");
		}else {
			System.out.println("����ʧ�ܣ�");
		}*/
		/*IUserDao userDao = new UserDaoImpl();
		User user = new User();
		user.setId(7);
		int rows = userDao.delete(user);
		if (rows>0) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");

		}*/
		/*IUserDao userDao = new UserDaoImpl();
		User user = new User(5, 12, "��С��", "Ů", "����", new Date(), 7561D);
		int rows = userDao.update(user);
		if (rows>0) {
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");

		}*/
		
		IUserDao userDao = new UserDaoImpl();
		User user = userDao.getById(3);
		System.out.println(user);
	}
}
