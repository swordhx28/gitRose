package com.yueqian.auction.mapper;

import com.yueqian.auction.pojo.AuctionRecord;
import com.yueqian.auction.pojo.AuctionRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionRecordMapper {
    int countByExample(AuctionRecordExample example);

    int deleteByExample(AuctionRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AuctionRecord record);

    int insertSelective(AuctionRecord record);

    List<AuctionRecord> selectByExample(AuctionRecordExample example);

    AuctionRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AuctionRecord record, @Param("example") AuctionRecordExample example);

    int updateByExample(@Param("record") AuctionRecord record, @Param("example") AuctionRecordExample example);

    int updateByPrimaryKeySelective(AuctionRecord record);

    int updateByPrimaryKey(AuctionRecord record);
}