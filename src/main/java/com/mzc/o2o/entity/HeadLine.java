package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;

@Data
public class HeadLine implements Serializable {
    @TableId
    private Integer lineId;

    @NotBlank(message = "头条名不能为空")
    private String lineName;

    @NotBlank(message = "头条链接不能为空")
    private String lineLink;

    @NotBlank(message = "头条图片不能为空")
    private String lineImg;

    @Range(min = 1, max = 10, message = "头条有效优先级范围1-10")
    private Integer priority;

    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;
}