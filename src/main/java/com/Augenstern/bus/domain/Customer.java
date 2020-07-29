package com.Augenstern.bus.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Customer {
    /**
    * 身份证
    */
    private String identity;

    /**
    * 姓名
    */
    private String custname;

    /**
    * 性别
    */
    private Integer sex;

    /**
    * 地址
    */
    private String address;

    /**
    * 电话
    */
    private String phone;

    /**
    * 职位
    */
    private String career;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
}