package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.Shop;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/31 22:13
 */
public interface ShopService extends IService<Shop> {

    Integer getCountByName(String shopName);
}
