package com.shida.cn.service;

import java.util.List;

import com.shida.cn.dao.model.Order;

public interface IOrderService {
	List<Order> getUserOrderList(Long id);
	int save(Order order);
}
