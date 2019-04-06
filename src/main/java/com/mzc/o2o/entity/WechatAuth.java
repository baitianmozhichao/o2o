package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class WechatAuth {
    @TableId
    private Integer wechatAuthId;

    private Integer userId;

    private String openId;

    private Date createTime;
}