package com.yueqian.auction.service;


import com.yueqian.auction.pojo.AuctionUser;

public interface UserSercice {
	
	
	public AuctionUser login(String username ,String userPassword) ;
	
	public void register(AuctionUser user);
}
