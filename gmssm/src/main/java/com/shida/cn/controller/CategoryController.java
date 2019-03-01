package com.shida.cn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shida.cn.dao.model.Category;
import com.shida.cn.service.ICategoryService;
import com.shida.cn.utils.Page;


@Controller
@RequestMapping("/categoryController")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping("/categoryList")
	public String categoryList(Integer page,Integer pageSize,HttpServletRequest request) {
		if(page==null||pageSize==null) {
			page = 1;
			pageSize = 5;
		}
		Page<Category> pageCategory = categoryService.getPageCategory(page, pageSize);
		request.setAttribute("categoryList", pageCategory);
		return "back/show_category1";
	}
	
	@RequestMapping("/insertCategory")
	public String insertCategory(String name) {
		Category category = new Category();
		category.setName(name);
		int row = categoryService.save(category);
		if(row > 0) {
			System.out.println("保存品牌成功！");
		} else {
			System.out.println("保存品牌失败！");
		}
		return "forward:/categoryController/categoryList";
	}
	
	@RequestMapping("/addCategory")
	public String addCategory() {
		return "back/add_category";
	}
}
