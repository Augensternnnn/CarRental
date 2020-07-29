package com.Augenstern.bus.vo;

import com.Augenstern.bus.domain.Customer;
import lombok.Data;

@Data
public class CustomerVo extends Customer {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 接受多个角色id
     */
    private String[] ids;
}
