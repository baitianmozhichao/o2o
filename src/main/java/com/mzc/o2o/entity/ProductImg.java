package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/27 22:41
 */
@Data
public class ProductImg {
    private Long productImgId;
    private String imgAddr;
    private String imgDesc;
    private Integer priority;
    private Date createTime;

    private Long productId;
}
