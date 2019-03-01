package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shida.cn.dao.model.User;
import com.shida.cn.service.IUserService;

public class TestUserServiceImpl {
	@Test
	public void testGetUserById() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring.xml","spring-mybatis.xml"});
		IUserService userService = (IUserService) context.getBean("userService");
		User user = userService.getUserById(1L);
		System.out.println(user.getLoginName());
	}
}
