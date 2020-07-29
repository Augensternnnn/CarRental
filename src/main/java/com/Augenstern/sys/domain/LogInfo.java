package com.Augenstern.sys.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 登录日志信息
 */
@Data
public class LogInfo {
    private Integer id;

    private String loginname;

    private String loginip;

    /**
     * 转换到页面上的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date logintime;
}