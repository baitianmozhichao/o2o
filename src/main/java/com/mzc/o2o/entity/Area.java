package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 区域
 * @Auther: mzc
 * @Date: 2019/3/26 21:37
 */
@Data
public class Area {
    private Integer areaId;
    private String areaName;
    private String areaDesc;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
}
