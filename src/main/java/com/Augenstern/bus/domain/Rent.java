package com.Augenstern.bus.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class Rent {
    private String rentid;

    private Double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    // 前台获取的时间进行格式化插入到数据库中
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")     // 后台数据库查询出来的时间转换到前台进行显示
    private Date begindate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss",timezone = "GTM+8")
    private Date returndate;

    private Integer rentflag;

    private String identity;

    private String carnumber;

    private String opername;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")     //后台数据库查询出来的时间转换到前台进行显示
    private Date createtime;

    public void setRentid(String rentid) {
        this.rentid = rentid == null ? null : rentid.trim();
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public void setRentflag(Integer rentflag) {
        this.rentflag = rentflag;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber == null ? null : carnumber.trim();
    }

    public void setOpername(String opername) {
        this.opername = opername == null ? null : opername.trim();
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}