package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ProductCategory implements Serializable {
    @TableId
    private Integer productCategoryId;

    @NotBlank(message = "商品分类名不能为空")
    private String productCategoryName;

//    @NotBlank(message = "商品描述不能为空")
    private String productCategoryDesc;

//    @Range(min = 1, max = 10, message = "优先级有效范围1-10")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    @NotNull(message = "商品所属商铺Id不能为空")
    private Integer shopId;
}