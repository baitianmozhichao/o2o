package com.mzc.o2o.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 08:46
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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