package com.Augenstern.sys.vo;

import com.Augenstern.sys.domain.User;
import lombok.Data;

@Data
public class UserVo extends User {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String code;

    /**
     * 接受多个角色id
     */
    private Integer[] ids;
}
