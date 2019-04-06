package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserShopMap {
    @TableId
    private Integer userShopId;

    private Integer userId;

    private Integer shopId;

    private String userName;

    private String shopName;

    private Date createTime;

    private Integer point;
}