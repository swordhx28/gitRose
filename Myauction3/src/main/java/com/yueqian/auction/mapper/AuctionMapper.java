package com.yueqian.auction.mapper;

import com.yueqian.auction.pojo.Auction;
import com.yueqian.auction.pojo.AuctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionMapper {
    int countByExample(AuctionExample example);

    int deleteByExample(AuctionExample example);

    int deleteByPrimaryKey(Integer auctionid);

    int insert(Auction record);

    int insertSelective(Auction record);

    List<Auction> selectByExample(AuctionExample example);

    Auction selectByPrimaryKey(Integer auctionid);

    int updateByExampleSelective(@Param("record") Auction record, @Param("example") AuctionExample example);

    int updateByExample(@Param("record") Auction record, @Param("example") AuctionExample example);

    int updateByPrimaryKeySelective(Auction record);

    int updateByPrimaryKey(Auction record);
}