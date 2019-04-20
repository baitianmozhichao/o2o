package com.mzc.o2o.enums;

/**
 * 登陆系统的身份枚举
 *
 * @auther: mzc
 */
public enum RoleTypeEnum {

    CUSTOMER(1, "买家"),
    SELLER(2, "卖家"),
    ADMIN(3, "管理员");

    private Integer roleType;
    private String roleTypeInfo;

    RoleTypeEnum(Integer roleType, String roleTypeInfo) {
        this.roleType = roleType;
        this.roleTypeInfo = roleTypeInfo;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public String getRoleTypeInfo() {
        return roleTypeInfo;
    }
}
