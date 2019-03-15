package com.yueqian.auction.pojo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AuctionUser {
    private Integer userid;

    @Size(min=3,max=6,message="{register.username.length.error}")
    private String username;
    @Size(min=6,message="{register.password.length.error}")
    private String userpassword;
    @Pattern(regexp="\\d{18}",message="{register.usercardno.format.error}")
    private String usercardno;

    private String usertel;

    private String useraddress;

    private String userpostnumber;

    private Integer userisadmin;

    private String userquestion;

    private String useranswer;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsercardno() {
        return usercardno;
    }

    public void setUsercardno(String usercardno) {
        this.usercardno = usercardno == null ? null : usercardno.trim();
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    public String getUserpostnumber() {
        return userpostnumber;
    }

    public void setUserpostnumber(String userpostnumber) {
        this.userpostnumber = userpostnumber == null ? null : userpostnumber.trim();
    }

    public Integer getUserisadmin() {
        return userisadmin;
    }

    public void setUserisadmin(Integer userisadmin) {
        this.userisadmin = userisadmin;
    }

    public String getUserquestion() {
        return userquestion;
    }

    public void setUserquestion(String userquestion) {
        this.userquestion = userquestion == null ? null : userquestion.trim();
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer == null ? null : useranswer.trim();
    }
}