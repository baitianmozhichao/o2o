package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:14
 */
public interface ShopCategoryService extends IService<ShopCategory> {

    List<ShopCategory> queryByParentId(Integer parentId);

    List<ShopCategory> queryRootCats();
}
