package com.yueqian.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yueqian.auction.mapper.AuctionUserMapper;
import com.yueqian.auction.pojo.AuctionUser;
import com.yueqian.auction.pojo.AuctionUserExample;
import com.yueqian.auction.service.UserSercice;
@Service
@Transactional
public class UserServiceImpl implements UserSercice {
	@Autowired
	private AuctionUserMapper auctionUserMapper;
	
	@Override
	public void register(AuctionUser user) {
		auctionUserMapper.insert(user);
	}

	@Override
	public AuctionUser login(String username, String userPassword) {
		AuctionUserExample example = new AuctionUserExample();
		AuctionUserExample.Criteria criteria = example.createCriteria();
		
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(userPassword);
		
		List<AuctionUser> list = auctionUserMapper.selectByExample(example);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}

}
