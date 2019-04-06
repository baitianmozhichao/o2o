package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class LocalAuth {

    @TableId
    private Integer localAuthId;

    private Integer userId;

    private String userName;

    private String password;

    private Date createTime;

    private Date lastEditTime;
}