package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shida.cn.dao.model.Category;
import com.shida.cn.service.ICategoryService;
import com.shida.cn.utils.Page;

public class TestCategory {
	
	@Test
	public void testPageCategory() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring.xml","spring-mybatis.xml"});
		ICategoryService categoryService = (ICategoryService) context.getBean("categoryService");
		Page<Category> pageCategory = categoryService.getPageCategory(2, 3);
		System.out.println(pageCategory.getCount());
		List<Category> lists = pageCategory.getLists();
		for (Category category : lists) {
			System.out.println(category.getName());
		}
	}
	
	@Test
	public void testCategoryAll() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring.xml","spring-mybatis.xml"});
		ICategoryService categoryService = (ICategoryService) context.getBean("categoryService");
		List<Category> list = categoryService.getAll();
		for (Category category : list) {
			System.out.println(category.getName());
		}
	}
	
	@Test
	public void testInsert() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring.xml","spring-mybatis.xml"});
		ICategoryService categoryService = (ICategoryService) context.getBean("categoryService");
		Category category = new Category();
		category.setId(123L);
		category.setName("洗碗机");
		category.setParentId(1L);
		int row = categoryService.save(category);
		if(row > 0) {
			System.out.println("保存成功！");
		}else {
			System.out.println("保存失败！");
		}
	}
	
	@Test
	public void testOneName() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring.xml","spring-mybatis.xml"});
		ICategoryService categoryService = (ICategoryService) context.getBean("categoryService");
		List<Category> categoryOneName = categoryService.getCategoryOneName();
		for (Category category : categoryOneName) {
			System.out.println(category.getName());
		}
	}
	
	@Test
	public void testTweName() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring.xml","spring-mybatis.xml"});
		ICategoryService categoryService = (ICategoryService) context.getBean("categoryService");
		List<Category> categoryOneName = categoryService.getCategoryTweName(17L);
		for (Category category : categoryOneName) {
			System.out.println(category.getName());
		}
	}
}
