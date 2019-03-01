package com.shida.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shida.cn.dao.CategoryMapper;
import com.shida.cn.dao.model.Category;
import com.shida.cn.service.ICategoryService;
import com.shida.cn.utils.Page;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	public Page<Category> getPageCategory(Integer page, Integer pageSize) {
		int currentPage = (page - 1) * pageSize;
		int totalCountCategory = categoryMapper.getTotalCountCategory();
		List<Category> list = categoryMapper.getListCategory(currentPage,pageSize);
		return new Page<Category>(currentPage,totalCountCategory,pageSize,list);
	}

	public int save(Category category) {
		return categoryMapper.insertSelective(category);
	}

	public List<Category> getAll() {
		return categoryMapper.getAll();
	}

	public List<Category> getCategoryOneName() {
		return categoryMapper.getCategoryOneName();
	}

	public List<Category> getCategoryTweName(Long id) {
		return categoryMapper.getCategoryTweName(id);
	}

	public Category getCategoryById(Long id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

}
