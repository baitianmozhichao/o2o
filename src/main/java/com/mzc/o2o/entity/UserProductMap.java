package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;

public class UserProductMap {
    @TableId
    private Integer userProductId;

    private Integer userId;

    private Integer productId;

    private Integer shopId;

    private String userName;

    private String productName;

    private Date createTime;

    private Integer point;

    public Integer getUserProductId() {
        return userProductId;
    }

    public void setUserProductId(Integer userProductId) {
        this.userProductId = userProductId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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