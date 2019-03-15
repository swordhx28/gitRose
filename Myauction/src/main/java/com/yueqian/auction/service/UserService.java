package com.yueqian.auction.service;


import com.yueqian.auction.pojo.AuctionUser;

public interface UserService {
	public AuctionUser login(String username,String password);
	
	public void register(AuctionUser auctionUser);
}
