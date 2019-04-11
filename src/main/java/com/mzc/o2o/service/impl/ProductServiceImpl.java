package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.ProductDao;
import com.mzc.o2o.entity.Product;
import com.mzc.o2o.service.ProductService;
import com.mzc.o2o.vo.ProductQueryCondition;
import com.mzc.o2o.vo.ProductVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:07
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Override
    public ProductVo queryByIdWithName(Integer productId) {
        return baseMapper.queryByIdWithName(productId);
    }

    @Override
    public List<Product> getByConditionPage(ProductQueryCondition condition, Integer current, Integer size) {
        EntityWrapper productWrapper = new EntityWrapper<Product>();

//        关键词搜索
        if (StringUtils.isNotEmpty(condition.getKey())) {
            productWrapper.andNew();
            productWrapper.like("product_name", condition.getKey());
            productWrapper.or().like("product_desc", condition.getKey());
        }

//        正常价区间
        if (condition.getNormalPriceMin() != null) {
            productWrapper.andNew();
            productWrapper.ge("normal_price", condition.getNormalPriceMin());
        }
        if (condition.getNormalPriceMin() != null) {
            productWrapper.andNew();
            productWrapper.le("normal_price", condition.getNormalPriceMax());
        }

//        推广价区间
        if (condition.getPromotionPriceMin() != null) {
            productWrapper.andNew();
            productWrapper.ge("promotion_price", condition.getPromotionPriceMin());
        }
        if (condition.getPromotionPriceMax() != null) {
            productWrapper.andNew();
            productWrapper.le("promotion_price", condition.getPromotionPriceMax());
        }

//        其他
        if (condition.getProductName() != null) {
            productWrapper.andNew();
            productWrapper.like("product_Name", condition.getProductName());
        }
        if (condition.getProductDesc() != null) {
            productWrapper.andNew();
            productWrapper.like("product_desc", condition.getProductDesc());
        }
        if (condition.getEnableStatus() != null) {
            productWrapper.andNew();
            productWrapper.eq("enableStatus", condition.getEnableStatus());
        }
        if (condition.getProductCategoryId() != null) {
            productWrapper.andNew();
            productWrapper.eq("product_category_id", condition.getProductCategoryId());
        }
        if (condition.getShopId() != null) {
            productWrapper.andNew();
            productWrapper.eq("shop_id", condition.getShopId());
        }
        return baseMapper.selectPage(new Page<Product>(current, size), productWrapper);
    }
}
