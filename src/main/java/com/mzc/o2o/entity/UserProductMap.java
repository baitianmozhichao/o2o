package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserProductMap {
    @TableId
    private Integer userProductId;

    private Integer userId;

    private Integer productId;

    private Integer shopId;

    private String userName;

    private String productName;

    private Date createTime;

    private Integer point;
}