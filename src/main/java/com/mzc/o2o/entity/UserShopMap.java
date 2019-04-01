package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;

public class UserShopMap {
    @TableId
    private Integer userShopId;

    private Integer userId;

    private Integer shopId;

    private String userName;

    private String shopName;

    private Date createTime;

    private Integer point;

    public Integer getUserShopId() {
        return userShopId;
    }

    public void setUserShopId(Integer userShopId) {
        this.userShopId = userShopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}