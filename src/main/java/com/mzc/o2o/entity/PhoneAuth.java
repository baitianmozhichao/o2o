package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;

public class PhoneAuth {
    @TableId
    private Integer phoneAuthId;

    private Integer userId;

    private String phone;

    private String authNumber;

    public Integer getPhoneAuthId() {
        return phoneAuthId;
    }

    public void setPhoneAuthId(Integer phoneAuthId) {
        this.phoneAuthId = phoneAuthId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAuthNumber() {
        return authNumber;
    }

    public void setAuthNumber(String authNumber) {
        this.authNumber = authNumber == null ? null : authNumber.trim();
    }
}