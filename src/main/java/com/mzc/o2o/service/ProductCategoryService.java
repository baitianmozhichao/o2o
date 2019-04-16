package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 22:59
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    List<Integer> getAllProCatIdList(Integer shopId);
}
