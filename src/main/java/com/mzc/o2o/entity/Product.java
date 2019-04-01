package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/27 22:36
 */
@Data
public class Product {
    private Long productId;
    private String productName;
    private String productDesc;
    private String imgAddr;
    private String normalPrice;
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;
    private Integer point;

    private ProducCategory productCategory;
    private Shop shop;
    private List<ProductImg> productImgList;
}
