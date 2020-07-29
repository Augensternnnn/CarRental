package com.Augenstern.bus.vo;

import com.Augenstern.bus.domain.Car;
import lombok.Data;

@Data
public class CarVo extends Car {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    //接受多个id
    private String [] ids;
}
