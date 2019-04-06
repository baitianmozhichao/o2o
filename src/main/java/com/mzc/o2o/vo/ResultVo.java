package com.mzc.o2o.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Controller返回
 * @auther: mzc
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResultVo<T> {
    private String resultCode;
    private String resultMsg;
    private T moudule;
    private Integer count;

    public ResultVo() {
    }

    private ResultVo(String resultCode, String resultMsg, T moudule, Integer count) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.moudule = moudule;
        this.count = count;
    }

    public ResultVo(String resultCode, String resultMsg, T moudule) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.moudule = moudule;
    }
}