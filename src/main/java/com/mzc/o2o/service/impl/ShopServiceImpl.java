package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.ShopDao;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.service.ShopService;
import com.mzc.o2o.vo.ShopQueryCondition;
import com.mzc.o2o.vo.ShopVo;
import org.apache.commons.lang.StringUtils;
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
    public List<Shop> queryByConditionsPage(ShopQueryCondition condition, Integer current, Integer size) {
        EntityWrapper shopWrapper = new EntityWrapper<Shop>();
        if(condition.getOwnerId()!=null){
            shopWrapper.eq("owner_id",condition.getOwnerId());
        }
        if (!StringUtils.isBlank(condition.getShopName())){
            shopWrapper.like("shop_name",condition.getShopName());
        }
        if(condition.getEnableStatus()!=null){
            shopWrapper.eq("enable_status",condition.getEnableStatus());
        }
        if(condition.getParentCategoryId()!=null){
            shopWrapper.eq("parent_category_id",condition.getParentCategoryId());
        }
        if(condition.getShopCategoryId()!=null){
            shopWrapper.eq("shop_category_id",condition.getShopCategoryId());
        }
        if(condition.getAreaId()!=null){
            shopWrapper.eq("area_id",condition.getAreaId());
        }
        //TODO
        shopWrapper.orderBy("priority");
        return baseMapper.selectPage(new Page<Shop>(current, size),shopWrapper);
    }
}
