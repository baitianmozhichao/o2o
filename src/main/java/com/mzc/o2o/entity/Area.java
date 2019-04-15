package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;

@Data
public class Area implements Serializable {

    @ApiModelProperty("区域id")
    @TableId
    private Integer areaId;

    @ApiModelProperty("区域名称")
    @NotBlank(message = "区域名称不能为空")
    private String areaName;

    @ApiModelProperty("区域描述")
    @NotBlank(message = "区域描述不能为空")
    private String areaDesc;

    @ApiModelProperty("区域优先级")
    @Range(min = 1, max = 10, message = "优先级有效范围1-10")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;
}