package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 22:51
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements ShopService {
    @Override
    public Integer getCountByName(String shopName) {
        List<Shop> shopList = dao.selectList(new EntityWrapper<Shop>().eq("shop_name",shopName));
        return shopList.size();
    }
}
