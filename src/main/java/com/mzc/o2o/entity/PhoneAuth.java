package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

@Data
public class PhoneAuth {
    @TableId
    private Integer phoneAuthId;

    private Integer userId;

    private String phone;

    private String authNumber;

}