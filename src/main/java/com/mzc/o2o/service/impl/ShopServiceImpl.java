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
        List<Shop> shopList = baseMapper.selectList(new EntityWrapper<Shop>().eq("shop_name", shopName));
        return shopList.size();
    }

    @Override
    public ShopVo queryShopWithName(Integer shopId) {
        return baseMapper.queryShopWithName(shopId);
    }

    /**
     * 分页、条件查询 【重要】
     * @param condition
     * @param current
     * @param size
     * @return
     */
    @Override
    public List<Shop> queryByConditionsPage(ShopQueryCondition condition, Integer current, Integer size) {
        EntityWrapper<Shop> shopWrapper = new EntityWrapper<>();

//        TODO 花了很久做出来，现在发现这个功能不需要!!
//          为搜索方便，传入的categoryId可以同事匹配“shop_category_id”和“parent_category_id”
//        if (condition.getShopCategoryId() != null) {
//            shopWrapper.eq("shop_category_id", condition.getShopCategoryId());
//            shopWrapper.or().eq("parent_category_id", condition.getShopCategoryId());
//        }

//        引入关键词搜索：商铺名|商铺描述
        if (StringUtils.isNotEmpty(condition.getKey())) {
            shopWrapper.andNew();
            shopWrapper.like("shop_name", condition.getKey());
            shopWrapper.or().like("shop_desc", condition.getKey());
        }

//        其他搜索条件
        if (condition.getOwnerId() != null) {
            shopWrapper.andNew();
            shopWrapper.eq("owner_id", condition.getOwnerId());
        }
        if (!StringUtils.isBlank(condition.getShopName())) {
            shopWrapper.andNew();
            shopWrapper.like("shop_name", condition.getShopName());
        }
        if (condition.getEnableStatus() != null) {
            shopWrapper.andNew();
            shopWrapper.eq("enable_status", condition.getEnableStatus());
        }
        if (condition.getAreaId() != null) {
            shopWrapper.andNew();
            shopWrapper.eq("area_id", condition.getAreaId());
        }
        if (condition.getShopCategoryId() != null) {
            shopWrapper.andNew();
            shopWrapper.eq("shop_category_id", condition.getShopCategoryId());
        }
        if (condition.getParentCategoryId() != null) {
            shopWrapper.andNew();
            shopWrapper.eq("parent_category_id", condition.getParentCategoryId());
        }

//        审核要通过，才能展示
//        shopWrapper.eq("enable_status", ShopStateEnum.SUCCESS.getState());

//        排序
        shopWrapper.orderBy("priority");
        return baseMapper.selectPage(new Page<Shop>(current, size), shopWrapper);
    }
}
