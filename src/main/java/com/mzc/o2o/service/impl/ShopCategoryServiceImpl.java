package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.ShopCategoryDao;
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
public class ShopCategoryServiceImpl extends ServiceImpl<ShopCategoryDao, ShopCategory> implements ShopCategoryService {
    @Override
    public List<ShopCategory> queryByParentId(Integer parentId) {
        return baseMapper.selectList(new EntityWrapper<ShopCategory>().eq("parent_id",parentId));
    }

    @Override
    public List<ShopCategory> queryRootCats() {
        return baseMapper.selectList(new EntityWrapper<ShopCategory>().eq("parent_id",0));
    }
}
