package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;

public class UserAwardMap {
    @TableId
    private Integer userAwardId;

    private Integer userId;

    private Integer awardId;

    private Integer shopId;

    private String userName;

    private String awardName;

    private Date expireTime;

    private Date createTime;

    private Integer usedStatus;

    private Integer point;

    public Integer getUserAwardId() {
        return userAwardId;
    }

    public void setUserAwardId(Integer userAwardId) {
        this.userAwardId = userAwardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
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

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(Integer usedStatus) {
        this.usedStatus = usedStatus;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}