package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/27 22:24
 */
@Data
public class ProducCategory {
    private Long productCategoryId;
    private String productCategoryName;
    private String productCategoryDesc;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;

    private Long shopId;
}

