package com.mzc.o2o.web;

import com.mzc.o2o.vo.ResultVo;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 22:34
 */
public class BaseController<T> {
    public ResultVo<T> buildEmptyResultVo() {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setResultCode("F");
        resultVo.setResultMsg("操作失败");
        return resultVo;
    }

    public ResultVo<T> buildResultVo(T moudule, Integer count) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setResultCode("T");
        resultVo.setResultMsg("返回成功");
        resultVo.setMoudule(moudule);
        resultVo.setCount(count);
        return resultVo;
    }
}
