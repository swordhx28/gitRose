package com.shida.cn.dao.model;

import java.util.Date;

public class User {
    private Long id;

    private String myName;

    private String loginName;

    private String pass;

    private String email;

    private String sex;

    private String userImg;

    private Date bornDay;
    
    private String nyear;
    
    private String nmonth;
    
    private String nday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName == null ? null : myName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

    public Date getBornDay() {
        return bornDay;
    }

    public void setBornDay(Date bornDay) {
        this.bornDay = bornDay;
    }

	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}

	public String getNmonth() {
		return nmonth;
	}

	public void setNmonth(String nmonth) {
		this.nmonth = nmonth;
	}

	public String getNday() {
		return nday;
	}

	public void setNday(String nday) {
		this.nday = nday;
	}
}