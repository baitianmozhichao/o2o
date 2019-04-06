package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Shop {

    @ApiModelProperty("商铺id")
    @TableId
    private Integer shopId;

    @ApiModelProperty("店长id")
    private Integer ownerId;

    @ApiModelProperty("区域id")
    private Integer areaId;

    @ApiModelProperty("商铺分类id")
    private Integer shopCategoryId;

    @ApiModelProperty("商铺父级分类id")
    private Integer parentCategoryId;

    @ApiModelProperty("商铺名")
    private String shopName;

    @ApiModelProperty("商铺描述")
    private String shopDesc;

    @ApiModelProperty("商铺地址")
    private String shopAddr;

    @ApiModelProperty("商铺联系电话")
    private String phone;

    @ApiModelProperty("商铺图片")
    private String shopImg;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    @ApiModelProperty("商铺优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    @ApiModelProperty("是否启用")
    private Integer enableStatus;

    @ApiModelProperty("建议")
    private String advice;
}