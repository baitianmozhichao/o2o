package com.mzc.o2o.web.admin;

import com.mzc.o2o.entity.HeadLine;
import com.mzc.o2o.service.HeadLineService;
import com.mzc.o2o.util.BindingResultUtil;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/headLine")
public class HeadLineController extends BaseController {

    @Autowired
    private HeadLineService headLineService;

    /**
     * 新增头条
     * @param headLine
     * @return
     */
    @PostMapping("/addHeadLine")
    public ResultVo<HeadLine> addHeadLine(@Valid HeadLine headLine, BindingResult bindingResult) {
        headLine.setCreateTime(new Date());
        headLine.setLastEditTime(new Date());
        if(bindingResult.hasErrors()){
            return buildFailResultVo(BindingResultUtil.bindResult2Str(bindingResult),0);
        }
        boolean result = headLineService.insert(headLine);
        if(result){
            return buildResultVo(headLine, 1);
        }
        return buildEmptyResultVo();
    }
}
