package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Area implements Serializable {

    @ApiModelProperty("区域id")
    @TableId
    private Integer areaId;

    @ApiModelProperty("区域名称")
    private String areaName;

    @ApiModelProperty("区域描述")
    private String areaDesc;

    @ApiModelProperty("区域优先级")
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;
}