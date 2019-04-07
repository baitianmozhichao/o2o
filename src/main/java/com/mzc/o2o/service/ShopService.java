package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.vo.ShopQueryCondition;
import com.mzc.o2o.vo.ShopVo;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/31 22:13
 */
public interface ShopService extends IService<Shop> {

    Integer getCountByName(String shopName);

    ShopVo queryShopWithName(Integer shopId);

    List<Shop> queryByConditionsPage(ShopQueryCondition condition, Integer current, Integer size);
}
