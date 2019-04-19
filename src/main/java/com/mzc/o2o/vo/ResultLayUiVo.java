package com.mzc.o2o.vo;

import lombok.Data;

/**
 * @auther: mzc
 */
@Data
public class ResultLayUiVo<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultLayUiVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultLayUiVo(T data) {
        this(0, "成功", data);
    }

    public ResultLayUiVo() {
        this(-1, "失败", null);
    }
}
