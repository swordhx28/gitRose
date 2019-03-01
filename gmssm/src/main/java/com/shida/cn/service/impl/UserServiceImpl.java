package com.shida.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shida.cn.dao.UserMapper;
import com.shida.cn.dao.model.User;
import com.shida.cn.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	public User getUserById(long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public User login(User user) {
		return userMapper.login(user);
	}

	public int save(User user) {
		return userMapper.insertSelective(user);
	}

}
