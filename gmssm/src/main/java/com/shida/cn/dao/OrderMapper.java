package com.shida.cn.dao;

import java.util.List;

import com.shida.cn.dao.model.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> getUserOrderList(Long id);
}