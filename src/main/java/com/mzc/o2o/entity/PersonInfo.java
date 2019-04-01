package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/26 23:09
 */
@Data
public class PersonInfo {
    private Long userId;
    private String name;
    private Date birthday;
    private String gender;
    private String phone;
    private String email;
    private String profileImg;
    private Integer customerFlag;
    private Integer shopOwnerFlag;
    private Integer adminFlag;
    private String enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
