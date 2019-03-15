package com.yueqian.auction.utils;

public class AuctionpriceException extends Exception {
	private String massage;

	
	
	
	
	public AuctionpriceException(String massage) {
		
		this.massage = massage;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}
	
}
