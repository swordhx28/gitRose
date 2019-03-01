package com.shida.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shida.cn.dao.OrderMapper;
import com.shida.cn.dao.model.Order;
import com.shida.cn.service.IOrderService;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	public List<Order> getUserOrderList(Long id) {
		return orderMapper.getUserOrderList(id);
	}

	public int save(Order order) {
		return orderMapper.insertSelective(order);
	}
	
}
