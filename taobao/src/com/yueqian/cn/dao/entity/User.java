package com.yueqian.cn.dao.entity;

import java.util.Date;

public class User {
	private Integer id;
	private Integer score;
	private String name;
	private String sex;
	private String address;
	private Date birthday;
	private Double money;
	
	public User() {
		super();
	}

	
	public User(Integer id, Integer score, String name, String sex, String address, Date birthday, Double money) {
		super();
		this.id = id;
		this.score = score;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.money = money;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", score=" + score + ", name=" + name + ", sex=" + sex + ", address=" + address
				+ ", birthday=" + birthday + ", money=" + money + "]";
	}
		

}
