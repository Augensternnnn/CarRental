package com.Augenstern.sys.domain;

import lombok.Data;

@Data
public class Role {
    private Integer roleid;

    private String rolename;

    private String roledesc;

    private Integer available;
}