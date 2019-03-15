package com.yueqian.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yueqian.auction.mapper.AuctionCustomMapper;
import com.yueqian.auction.mapper.AuctionMapper;
import com.yueqian.auction.mapper.AuctionRecordMapper;
import com.yueqian.auction.pojo.Auction;
import com.yueqian.auction.pojo.AuctionCustom;
import com.yueqian.auction.pojo.AuctionExample;
import com.yueqian.auction.pojo.AuctionRecord;
import com.yueqian.auction.pojo.AuctionRecordExample;
import com.yueqian.auction.service.AuctionService;
import com.yueqian.auction.utils.AuctionpriceException;
@Service
@Transactional
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionMapper auctionMapper;
	
	@Autowired
	private AuctionCustomMapper auctionCustomMapper;
	
	@Autowired
	private AuctionRecordMapper recordMapper;
	

	
	@Override
	public List<Auction> findAuctions(Auction auction) {
		AuctionExample example = new AuctionExample();
		AuctionExample.Criteria criteria = example.createCriteria();
		if (auction != null) {
			//1.商品名称的模糊查询
			if (auction.getAuctionname()!=null && !"".equals(auction.getAuctionname())) {
				criteria.andAuctionnameLike("%"+auction.getAuctionname()+"%");
			}
			
			//2.商品的描述模糊查询
			if (auction.getAuctiondesc() != null && !"".equals(auction.getAuctiondesc())) {
				criteria.andAuctiondescLike("%"+auction.getAuctiondesc()+"%");
			}
			//3.大于开始时间
			if (auction.getAuctionstarttime() !=null) {
				criteria.andAuctionstarttimeGreaterThan(auction.getAuctionstarttime());
			}
			//4.小于结束时间
			if (auction.getAuctionendtime() != null) {
				criteria.andAuctionendtimeLessThan(auction.getAuctionendtime());
			}
			//5.大于起拍金额
			if (auction.getAuctionstartprice() !=null) {
				criteria.andAuctionstartpriceGreaterThan(auction.getAuctionstartprice());
			}
		}
		//商品以起拍时间降序排序
		example.setOrderByClause("auctionstarttime desc");
		List<Auction> list = auctionMapper.selectByExample(example);

		return list;
	}

	@Override
	public Auction findAuctionRecordList(int auction) {
		
		
		return auctionCustomMapper.findAuctionRecordList(auction);
	}

	@Override
	public void addAuctionRecord(AuctionRecord record) throws Exception {
		
		Auction auction = auctionCustomMapper.findAuctionRecordList(record.getAuctionid());
		//判断时间
		if (auction.getAuctionendtime().after(new Date()) == false) {
			throw new AuctionpriceException("拍卖时间已结束");
		}else {
			if (auction.getAuctionrecordList().size()>0) {//判断是否有竞拍记录
				    AuctionRecord maxRecord = auction.getAuctionrecordList().get(0);
				    if (record.getAuctionprice() <= maxRecord.getAuctionprice()) {
						throw new AuctionpriceException("价格必须高于所有竞价的价格");
					}
			}else {//首次竞拍
				if (record.getAuctionprice()<= auction.getAuctionstartprice()) {
					throw new AuctionpriceException("价格必须高于起拍价");
				}
			}
		}
		recordMapper.insert(record);
	}

	@Override
	public List<AuctionCustom> findAuctionEndtime() {
		
		
		return auctionCustomMapper.findAuctionEndtime();
	}

	@Override
	public List<Auction> findAuctionNoendtime() {

		
		return auctionCustomMapper.findAuctionNoendtime();
	}

	@Override
	public void addAuction(Auction auction) {
		// TODO Auto-generated method stub
		auctionMapper.insert(auction);
	}
	
	@Override
	public Auction findAuctionAndRecordList(int auctionid) {
		
		return auctionCustomMapper.findAuctionRecordList(auctionid);
	}

	@Override
	public void updateAuction(Auction auction) {
		// TODO Auto-generated method stub
		auctionMapper.updateByPrimaryKey(auction);
	}

	@Override
	public Auction findAuctionById(int auctionid) {
		// TODO Auto-generated method stub
		return auctionMapper.selectByPrimaryKey(auctionid);
	}

	@Override
	public void removeAuction(int auctionid) {
		// 先删除子表数据
		AuctionRecordExample example = new AuctionRecordExample();
		AuctionRecordExample.Criteria criteria = example.createCriteria();
		criteria.andAuctionidEqualTo(auctionid);
		recordMapper.deleteByExample(example);
		
		//删除主表数据
		auctionMapper.deleteByPrimaryKey(auctionid);
		
	}

}
