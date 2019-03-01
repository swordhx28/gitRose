package com.shida.cn.dao;

import java.util.List;

import com.shida.cn.dao.model.Category;
import com.shida.cn.utils.Page;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    Page<Category> getPageCategory(Integer page, Integer pageSize);
    
    List<Category> getListCategory(Integer page, Integer pageSize);
    
    int getTotalCountCategory();
    
    List<Category> getAll();
    
    List<Category> getCategoryOneName();
    
    List<Category> getCategoryTweName(Long id);
}