package com.yueqian.cn.dao;

import com.yueqian.cn.dao.entity.User;

public interface IUserDao {
	
	public int save(User user);
	public int delete(User user);
	public int update(User user);
	public User getById(int id);
}
