package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserAwardMap {
    @TableId
    private Integer userAwardId;

    private Integer userId;

    private Integer awardId;

    private Integer shopId;

    private String userName;

    private String awardName;

    private Date expireTime;

    private Date createTime;

    private Integer usedStatus;

    private Integer point;
}