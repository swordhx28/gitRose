package com.yueqian.cn.dao.impl;

import com.yueqian.cn.dao.entity.User;

public interface StudentDao {
	public int save(User user);
	public int delete(User user);
	public int update(User user);
	public User getById(int id);
}
