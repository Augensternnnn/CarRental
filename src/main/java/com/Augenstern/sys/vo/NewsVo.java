package com.Augenstern.sys.vo;

import com.Augenstern.sys.domain.News;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NewsVo extends News {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 将前台页面的时间转换到后端
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 接受多个id
     */
    private Integer[] ids;
}
