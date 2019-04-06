package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ProductImg {
    @TableId
    private Integer productImgId;

    private String imgAddr;

    private String imgDesc;

    private Integer priority;

    private Date createTime;

    private Integer productId;
}