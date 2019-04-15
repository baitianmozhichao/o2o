package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonInfo implements Serializable {
    @TableId
    private Integer userId;

    @NotBlank(message = "名字不能为空")
    private String name;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date birthday;

    private String gender;

    @NotBlank(message = "联系方式不能为空")
    private String phone;

    @Email
    private String email;

    @URL
    private String profileImg;

    private Integer customerFlag;

    private Integer shopOwnerFlag;

    private Integer adminFlag;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;
}