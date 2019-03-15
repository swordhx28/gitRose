package com.yueqian.auction.service;

import java.util.List;

import com.yueqian.auction.pojo.Auction;
import com.yueqian.auction.pojo.AuctionCustom;
import com.yueqian.auction.pojo.AuctionRecord;

public interface AuctionService {

	public List<Auction> findAuctions(Auction auction);

	public Auction findAuctionRecordList(int auction);

	public void addAuctionRecord(AuctionRecord record) throws Exception;

	public List<AuctionCustom> findAuctionEndtime();

	public List<Auction> findAuctionNoendtime();
	
	public void addAuction(Auction auction);
	
	public Auction findAuctionAndRecordList(int auctionid);
	
	public void updateAuction(Auction auction);
	
	public Auction findAuctionById(int auctionid);
	
	public void removeAuction(int auctionid);
}
