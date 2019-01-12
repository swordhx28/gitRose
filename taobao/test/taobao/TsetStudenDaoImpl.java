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
		User user = new User(null, 23, "李小狗", "女", "水星", new Date(), 321D);
		int rows = userDao.save(user);
		if (rows>0) {
			System.out.println("保存成功！");
		}else {
			System.out.println("保存失败！");
		}*/
		
		
		
		/*StudentDao userDao = new StudentDaoImpl();
		User user = new User();
		user.setId(1);
		int rows = userDao.delete(user);
		if (rows>0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");

		}*/
		
		
		
		/*StudentDao userDao = new StudentDaoImpl();
		User user = new User(5, 12, "王小狗", "女", "火星", new Date(), 7561D);
		int rows = userDao.update(user);
		if (rows>0) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");

		}*/
		
		
		
		StudentDao userDao = new StudentDaoImpl();
		User user = userDao.getById(2);
		System.out.println(user);
	}

	
}
