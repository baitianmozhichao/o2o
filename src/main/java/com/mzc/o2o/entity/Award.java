package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Data
public class Award {

    @TableId
    private Integer awardId;

    @NotBlank(message = "奖励名不能为空")
    private String awardName;

    @NotBlank(message = "奖励描述不能为空")
    private String awardDesc;

    @URL
    private String awardImg;

    private Integer point;

    private Integer priority;

    private Date createTime;

    private Date expireTime;

    private Date lastEditTime;

    private Integer enableStatus;

    @NotBlank(message = "奖励所属商铺Id不能为空")
    private Integer shopId;
}