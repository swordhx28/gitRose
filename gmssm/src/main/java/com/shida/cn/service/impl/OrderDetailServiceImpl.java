package com.shida.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shida.cn.dao.OrderDetailMapper;
import com.shida.cn.dao.model.OrderDetail;
import com.shida.cn.service.IOrderDetailService;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements IOrderDetailService {
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	public OrderDetail getOrderDetailByOrderId(Long id) {
		return orderDetailMapper.getOrderDetailByOrderId(id);
	}

	public int save(OrderDetail orderDetail) {
		return orderDetailMapper.insertSelective(orderDetail);
	}
	
}
