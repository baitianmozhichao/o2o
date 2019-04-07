package com.mzc.o2o.web.common;

import com.mzc.o2o.enums.ShopStateEnum;
import com.mzc.o2o.vo.ShopStateEnumVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/common/status")
public class StatusController {

    /**
     * 获取商铺状态列表
     * @return
     */
    @GetMapping("/getShopStatusList")
    public List<ShopStateEnumVo> getShopStatusList(){
        List<ShopStateEnumVo> voList = new ArrayList<>();
        ShopStateEnum[] enums = ShopStateEnum.values();
        for (ShopStateEnum stateEnum: enums){
            voList.add(new ShopStateEnumVo(stateEnum.getState(),stateEnum.getStateInfo()));
        }
        return voList;
    }
}
