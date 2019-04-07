package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShopCategory implements Serializable {

    @ApiModelProperty("商铺分类id")
    @TableId
    private Integer shopCategoryId;

    @ApiModelProperty("商铺分类名")
    private String shopCategoryName;

    @ApiModelProperty("商铺分类描述")
    private String shopCategoryDesc;

    @ApiModelProperty("商铺分类图")
    private String shopCategoryImg;

    @ApiModelProperty("商铺优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    @ApiModelProperty("商铺分类父级id")
    private Integer parentId;
}