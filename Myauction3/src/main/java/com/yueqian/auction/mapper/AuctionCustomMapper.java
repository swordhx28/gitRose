package com.yueqian.auction.mapper;

import java.util.List;

import com.yueqian.auction.pojo.Auction;
import com.yueqian.auction.pojo.AuctionCustom;

public interface AuctionCustomMapper {

	
	public Auction findAuctionRecordList(int auction);
	
	public List<AuctionCustom> findAuctionEndtime();
	
	public List<Auction> findAuctionNoendtime();
}
