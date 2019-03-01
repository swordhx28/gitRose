package com.shida.cn.dao;

import java.util.List;

import com.shida.cn.dao.model.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> showIndexProdut();
}