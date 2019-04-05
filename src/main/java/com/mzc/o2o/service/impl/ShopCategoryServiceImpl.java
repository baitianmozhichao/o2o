package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.entity.ShopCategory;
import com.mzc.o2o.service.ShopCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:14
 */
@Service
public class ShopCategoryServiceImpl extends BaseServiceImpl<ShopCategory> implements ShopCategoryService {
    @Override
    public List<ShopCategory> queryByParentId(Integer parentId) {
        return dao.selectList(new EntityWrapper<ShopCategory>().eq("parent_id",parentId));
    }

    @Override
    public List<ShopCategory> queryRootCats() {
        return dao.selectList(new EntityWrapper<ShopCategory>().eq("parent_id",0));
    }
}
