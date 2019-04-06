package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class PersonInfo {
    @TableId
    private Integer userId;

    private String name;

    private Date birthday;

    private String gender;

    private String phone;

    private String email;

    private String profileImg;

    private Integer customerFlag;

    private Integer shopOwnerFlag;

    private Integer adminFlag;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;
}