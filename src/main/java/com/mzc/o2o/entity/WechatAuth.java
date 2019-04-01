package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/27 21:37
 */
@Data
public class WechatAuth {
    private Long wechatAuthId;
    private String openId;
    private Date createTime;

    private PersonInfo user;
}
