package com.mzc.o2o.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @auther: mzc
 */
@Data
public class ProductVo {
    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("商品名字")
    private String productName;

    @ApiModelProperty("商品描述")
    private String productDesc;

    @ApiModelProperty("商品图片地址")
    private String imgAddr;

    @ApiModelProperty("商品正常价")
    private String normalPrice;

    @ApiModelProperty("商品推广价")
    private String promotionPrice;

    @ApiModelProperty("商品优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    @ApiModelProperty("商品状态")
    private Integer enableStatus;

    private Integer point;

    @ApiModelProperty("商品类别id")
    private Integer productCategoryId;

    @ApiModelProperty("商品所属商铺id")
    private Integer shopId;

    private String productCategoryName;
    private String shopName;
}
