package com.yueqian.auction.service.impl;

import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yueqian.auction.mapper.AuctionUserMapper;
import com.yueqian.auction.pojo.AuctionUser;
import com.yueqian.auction.pojo.AuctionUserExample;
import com.yueqian.auction.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
		@Autowired
		private AuctionUserMapper AuctionUserMapper;
	
	@Override
	public AuctionUser login(String username, String password) {
		AuctionUserExample example = new AuctionUserExample();
		AuctionUserExample.Criteria criteria = example.createCriteria();
		
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(password);
		List<AuctionUser> list = AuctionUserMapper.selectByExample(example);
		if (list !=null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void register(AuctionUser auctionUser) {
		auctionUser.setUserisadmin(0);
		AuctionUserMapper.insert(auctionUser);
		
	}

	
		
	
	
	
	
	

}
