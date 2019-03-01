package com.shida.cn.dao;

import com.shida.cn.dao.model.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
    
    OrderDetail getOrderDetailByOrderId(Long id);
}