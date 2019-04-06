package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.ShopDao;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.service.ShopService;
import com.mzc.o2o.vo.ShopVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 22:51
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopDao, Shop> implements ShopService {
    @Override
    public Integer getCountByName(String shopName) {
        List<Shop> shopList = baseMapper.selectList(new EntityWrapper<Shop>().eq("shop_name",shopName));
        return shopList.size();
    }

    @Override
    public ShopVo queryShopWithName(Integer shopId) {
        return baseMapper.queryShopWithName(shopId);
    }

    @Override
    public List<Shop> queryByOwnerId(Integer ownerId) {
        return baseMapper.selectList(new EntityWrapper<Shop>().eq("owner_id",ownerId));
    }
}
