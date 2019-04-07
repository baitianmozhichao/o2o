package com.mzc.o2o.web;

import com.mzc.o2o.service.AwardService;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/3 08:43
 */
@RestController
@RequestMapping("/award")
public class AwardController extends BaseController {

    @Autowired
    private AwardService awardService;

    @GetMapping("/getByShopId/{shopId}")
    public ResultVo getByShopId(@PathVariable("shopId")Integer shopId){
        return buildResultVo(awardService.findByShopId(shopId),1);
    }
}
