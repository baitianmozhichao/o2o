package com.mzc.o2o.vo;

import lombok.Data;

/**
 * @auther: mzc
 */
@Data
public class ProductQueryCondition {

    private String productName;
    private String productDesc;

    /**
     * 正常价区间
     */
    private String normalPriceMin;
    private String normalPriceMax;

    /**
     * 推广价区间
     */
    private String promotionPriceMin;
    private String promotionPriceMax;

    private Integer enableStatus;
    private Integer productCategoryId;
    private Integer shopId;
}
