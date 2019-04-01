package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/27 21:38
 */
@Data
public class LocalAuth {
    private Long localAuthId;
    private String userName;
    private String password;
    private Date createTime;
    private Date lastEditTime;

    private PersonInfo user;
}
