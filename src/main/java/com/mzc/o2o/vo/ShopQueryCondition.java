package com.mzc.o2o.vo;

import lombok.Data;

/**
 * 商铺查询条件类
 *
 * @auther: mzc
 */
@Data
public class ShopQueryCondition {

    private Integer ownerId;
    private String shopName;
    private Integer enableStatus;
//    private Integer parentCategoryId;
    private Integer shopCategoryId;
    private Integer areaId;

//    搜索关键词
    private String key;
}
