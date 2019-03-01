package com.shida.cn.service;

import java.util.List;

import com.shida.cn.dao.model.Category;
import com.shida.cn.utils.Page;

public interface ICategoryService {
	
	Page<Category> getPageCategory(Integer page, Integer pageSize);
	
    int save(Category category);
    
    List<Category> getAll();
    
    List<Category> getCategoryOneName();
    
    List<Category> getCategoryTweName(Long id);
    
    Category getCategoryById(Long id);
}
