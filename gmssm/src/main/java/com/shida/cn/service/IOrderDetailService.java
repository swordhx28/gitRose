package com.shida.cn.service;

import com.shida.cn.dao.model.OrderDetail;

public interface IOrderDetailService {
	OrderDetail getOrderDetailByOrderId(Long id);
	int save(OrderDetail orderDetail);
}
