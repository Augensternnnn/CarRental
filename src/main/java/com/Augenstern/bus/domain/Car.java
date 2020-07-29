package com.Augenstern.bus.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;

@Getter
public class Car {
    private String carnumber;

    private String cartype;

    private String color;

    private Double price;

    private Double rentprice;

    private Double deposit;

    private Integer isrenting;

    private String description;

    private String carimg;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss",timezone = "GTM+8")
    private Date createtime;

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber == null ? null : carnumber.trim();
    }

    public void setCartype(String cartype) {
        this.cartype = cartype == null ? null : cartype.trim();
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRentprice(Double rentprice) {
        this.rentprice = rentprice;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public void setIsrenting(Integer isrenting) {
        this.isrenting = isrenting;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public void setCarimg(String carimg) {
        this.carimg = carimg == null ? null : carimg.trim();
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}