package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Data
public class Product {
    @TableId
    private Integer productId;

    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @NotBlank(message = "商品描述不能为空")
    private String productDesc;

    @URL
    private String imgAddr;

    @NotBlank(message = "商品价格不能为空")
    private String normalPrice;

    private String promotionPrice;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;

    @Range(min = 1, max = 10, message = "优先级有效范围1-10")
    private Integer point;

    private Integer productCategoryId;

    @NotBlank(message = "商品所属的商铺Id不能为空")
    private Integer shopId;
}