package com.shida.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shida.cn.dao.ProductMapper;
import com.shida.cn.dao.model.Product;
import com.shida.cn.service.IProductService;

@Service("productService")
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductMapper productMapper;

	public int save(Product product) {
		return productMapper.insertSelective(product);
	}

	public List<Product> showIndexProdut() {
		return productMapper.showIndexProdut();
	}

	public int updateViewNum(Product product) {
		return productMapper.updateByPrimaryKeySelective(product);
	}

	public Product getProductById(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}
	
}
