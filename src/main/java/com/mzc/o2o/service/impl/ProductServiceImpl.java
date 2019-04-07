package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.ProductDao;
import com.mzc.o2o.entity.Product;
import com.mzc.o2o.service.ProductService;
import com.mzc.o2o.vo.ProductQueryCondition;
import com.mzc.o2o.vo.ProductVo;
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
        EntityWrapper entityWrapper = new EntityWrapper<Product>();

//        正常价区间
        if (condition.getNormalPriceMin() != null) {
            entityWrapper.ge("normal_price", condition.getNormalPriceMin());
        }
        if (condition.getNormalPriceMin() != null) {
            entityWrapper.le("normal_price", condition.getNormalPriceMax());
        }

//        推广价区间
        if (condition.getPromotionPriceMin() != null) {
            entityWrapper.ge("promotion_price", condition.getPromotionPriceMin());
        }
        if (condition.getPromotionPriceMax() != null) {
            entityWrapper.le("promotion_price", condition.getPromotionPriceMax());
        }

//        其他
        if (condition.getProductName() != null) {
            entityWrapper.like("product_Name", condition.getProductName());
        }
        if (condition.getProductDesc() != null) {
            entityWrapper.like("product_desc", condition.getProductDesc());
        }
        if (condition.getEnableStatus() != null) {
            entityWrapper.le("enableStatus", condition.getEnableStatus());
        }
        if (condition.getProductCategoryId() != null) {
            entityWrapper.le("product_category_id", condition.getProductCategoryId());
        }
        if (condition.getShopId() != null) {
            entityWrapper.le("shop_id", condition.getShopId());
        }
        return baseMapper.selectPage(new Page<Product>(current, size), entityWrapper);
    }
}
