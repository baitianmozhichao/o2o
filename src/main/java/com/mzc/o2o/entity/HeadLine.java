package com.mzc.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/27 21:51
 */
@Data
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
//    0:不可用，1：可用
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
