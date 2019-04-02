package com.mzc.o2o.web;

import com.mzc.o2o.vo.ResultVo;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 22:34
 */
public class BaseController {
    public ResultVo buildEmptyResultVo() {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCode("T");
        resultVo.setResultMsg("无记录");
        return resultVo;
    }

    public ResultVo buildResultVo(Object moudule, Integer count) {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCode("T");
        resultVo.setResultMsg("返回成功");
        resultVo.setMoudule(moudule);
        resultVo.setCount(count);
        return resultVo;
    }
}
