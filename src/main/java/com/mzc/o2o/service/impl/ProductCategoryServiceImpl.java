package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.ProductCategoryDao;
import com.mzc.o2o.entity.ProductCategory;
import com.mzc.o2o.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:00
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDao,ProductCategory> implements ProductCategoryService {
    @Override
    public List<Integer> getAllProCatIdList(Integer shopId) {
        return baseMapper.getAllProCatIdList(shopId);
    }
}