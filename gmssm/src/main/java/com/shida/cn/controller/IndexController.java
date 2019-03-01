package com.shida.cn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shida.cn.dao.model.Category;
import com.shida.cn.dao.model.Product;
import com.shida.cn.service.ICategoryService;
import com.shida.cn.service.IProductService;

@Controller
@RequestMapping("")
public class IndexController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IProductService productService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<Category> categoryOneName = categoryService.getCategoryOneName();
		List<Category> categoryTweName = new ArrayList<Category>();
		Map<Category,List<Category>> cateMap = new HashMap<Category,List<Category>>();
		for (Category category : categoryOneName) {
			Long id = category.getId();
			categoryTweName = categoryService.getCategoryTweName(id);
			cateMap.put(category, categoryTweName);
		}
		List<Product> showIndexProdut = productService.showIndexProdut();
		request.setAttribute("showIndexProdut", showIndexProdut);
		request.setAttribute("categoryName", cateMap);
		return "/index";
	}
	
	@RequestMapping("/header")
	public String header(HttpServletRequest request) {
		List<Category> categoryOneName = categoryService.getCategoryOneName();
		request.setAttribute("categoryName", categoryOneName);
		return "/header";
	}
}
