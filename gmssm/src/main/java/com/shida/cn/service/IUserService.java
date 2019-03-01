package com.shida.cn.service;

import com.shida.cn.dao.model.User;

public interface IUserService {
	public User getUserById(long id);
	public User login(User user);
	public int save(User user);
}
