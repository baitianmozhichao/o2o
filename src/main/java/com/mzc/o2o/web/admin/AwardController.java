package com.mzc.o2o.web.admin;

import com.mzc.o2o.entity.Award;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.service.AwardService;
import com.mzc.o2o.util.BindingResultUtil;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    /**
     * 获取shopId的所有Award
     * @param shopId
     * @return
     */
    @GetMapping("/getByShopId/{shopId}")
    public ResultVo<List<Award>> getByShopId(@PathVariable("shopId")Integer shopId){
        List<Award> awardList = awardService.findByShopId(shopId);
        return buildResultVo(awardList,awardList.size());
    }

    /**
     * 新增奖励
     * @param award
     * @param bindingResult
     * @return
     */
    @PostMapping("/addAward")
    public ResultVo<Award> addAward(@Valid Award award, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return buildFailResultVo(BindingResultUtil.bindResult2Str(bindingResult),0);
        }
        boolean effectNum = awardService.insert(award);
        if(effectNum){
            return buildResultVo(award, 1);
        }
        return buildResultVo("插入错误", 0);
    }

    /**
     * 获取某个Award所属的商铺
     * @param awardId
     * @return
     */
    @GetMapping("/findShopId/{awardId}")
    public ResultVo<Shop> findShopId(@PathVariable("awardId") Integer awardId){
        return buildFailResultVo(awardService.selectById(awardId), 1);
    }
}
