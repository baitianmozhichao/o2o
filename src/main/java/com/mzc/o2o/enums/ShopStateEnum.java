package com.mzc.o2o.enums;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/31 21:33
 */
public enum ShopStateEnum {

    CHECK(0,"审核中"),
    OFFLINE(-1,"非法店铺"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    INNER_ERROR(-1001,"系统内部错误");

    private Integer state;
    private String stateInfo;

    ShopStateEnum(Integer state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public static boolean isExit(int status){
        for (ShopStateEnum shop: values()){
            if(shop.getState().equals(status)){
                return true;
            }
        }
        return false;
    }

    public static ShopStateEnum stateOf(int state){
        for (ShopStateEnum shop: values()){
            if(shop.getState().equals(state)){
                return shop;
            }
        }
        return null;
    }
}
