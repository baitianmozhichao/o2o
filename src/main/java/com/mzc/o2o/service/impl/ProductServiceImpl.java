package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.ProductDao;
import com.mzc.o2o.entity.Product;
import com.mzc.o2o.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:07
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao,Product> implements ProductService {
}
