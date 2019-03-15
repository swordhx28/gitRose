package com.yueqian.auction.mapper;

import com.yueqian.auction.pojo.AuctionUser;
import com.yueqian.auction.pojo.AuctionUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionUserMapper {
    int countByExample(AuctionUserExample example);

    int deleteByExample(AuctionUserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(AuctionUser record);

    int insertSelective(AuctionUser record);

    List<AuctionUser> selectByExample(AuctionUserExample example);

    AuctionUser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") AuctionUser record, @Param("example") AuctionUserExample example);

    int updateByExample(@Param("record") AuctionUser record, @Param("example") AuctionUserExample example);

    int updateByPrimaryKeySelective(AuctionUser record);

    int updateByPrimaryKey(AuctionUser record);
}