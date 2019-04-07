package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.Product;
import com.mzc.o2o.vo.ProductQueryCondition;
import com.mzc.o2o.vo.ProductVo;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:06
 */
public interface ProductService extends IService<Product> {

    ProductVo queryByIdWithName(Integer productId);

    List<Product> getByConditionPage(ProductQueryCondition condition,Integer current, Integer size);
}
