package com.yueqian.auction.pojo;

import java.lang.Double;
import java.util.Date;

public class AuctionRecord {
    private Integer id;

    private Integer userid;

    private Integer auctionid;

    private Date auctiontime;

    private Double auctionprice;
    
    private AuctionUser user;

    public AuctionUser getUser() {
		return user;
	}

	public void setUser(AuctionUser user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Date getAuctiontime() {
        return auctiontime;
    }

    public void setAuctiontime(Date auctiontime) {
        this.auctiontime = auctiontime;
    }

    public Double getAuctionprice() {
        return auctionprice;
    }

    public void setAuctionprice(Double auctionprice) {
        this.auctionprice = auctionprice;
    }
}