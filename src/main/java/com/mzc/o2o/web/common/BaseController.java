package com.mzc.o2o.web.common;

import com.mzc.o2o.vo.ResultLayUiVo;
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

    /**
     * 带分页信息的返回类型
     * @param moudule
     * @param count
     * @param current
     * @param size
     * @return
     */
    public ResultVo<T> buildResultVoPage(T moudule, Integer count,Integer current,Integer size) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setResultCode("T");
        resultVo.setResultMsg("返回成功");
        resultVo.setMoudule(moudule);
        resultVo.setCount(count);
        resultVo.setCurrent(current);
        resultVo.setSize(size);
        return resultVo;
    }

    public ResultVo<T> buildFailResultVo(T moudule, Integer count) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setResultCode("F");
        resultVo.setResultMsg("返回失败");
        resultVo.setMoudule(moudule);
        resultVo.setCount(count);
        return resultVo;
    }

    /**
     * layui需要的数据格式
     * @param data
     * @return
     */
    public ResultLayUiVo<T> buildResultLayUiVo(T data){
        return new ResultLayUiVo<>(data);
    }

    public ResultLayUiVo<T> buildFailResultLayUiVo(){
        return new ResultLayUiVo<>();
    }
}
