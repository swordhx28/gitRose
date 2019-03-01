package com.shida.cn.service;

import java.util.List;

import com.shida.cn.dao.model.Product;

public interface IProductService {
	int save(Product product);
	List<Product> showIndexProdut();
	int updateViewNum(Product product);
	Product getProductById(Long id);
}
