package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ProductCategory {
    @TableId
    private Integer productCategoryId;

    private String productCategoryName;

    private String productCategoryDesc;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private Integer shopId;
}