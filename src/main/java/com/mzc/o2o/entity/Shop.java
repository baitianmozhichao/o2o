package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;

@Data
public class Shop implements Serializable {

    @ApiModelProperty("商铺id")
    @TableId
    private Integer shopId;

    @ApiModelProperty("店长id")
    @NotBlank(message = "店长id不可为空")
    private Integer ownerId;

    @ApiModelProperty("区域id")
    @NotBlank(message = "区域id不可为空")
    private Integer areaId;

    @ApiModelProperty("商铺分类id")
    @NotBlank(message = "商铺分类id不可为空")
    private Integer shopCategoryId;

    @ApiModelProperty("商铺父级分类id")
    @NotBlank(message = "商铺父级分类id不可为空")
    private Integer parentCategoryId;

    @ApiModelProperty("商铺名")
    @NotBlank(message = "商铺名不可为空")
    private String shopName;

    @ApiModelProperty("商铺描述")
    private String shopDesc;

    @ApiModelProperty("商铺地址")
    private String shopAddr;

    @ApiModelProperty("商铺联系电话")
    @NotBlank(message = "商铺联系电话不可为空")
    private String phone;

    @ApiModelProperty("商铺图片")
    private String shopImg;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    @ApiModelProperty("商铺优先级")
    @Range(min = 1, max = 10, message = "商铺有效优先级范围1-10")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    @ApiModelProperty("是否启用")
    private Integer enableStatus;

    @ApiModelProperty("建议")
    private String advice;
}