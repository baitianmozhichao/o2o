package com.mzc.o2o.enums;

/**
 * @auther: mzc
 */
public enum HeadLineStateEnum {

    ACTIVE(1, "生效"),
    INVALID(0, "失效");

    private Integer code;
    private String info;

    private HeadLineStateEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
