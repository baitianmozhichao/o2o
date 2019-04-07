package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.dao.ShopCategoryDao;
import com.mzc.o2o.entity.ShopCategory;
import com.mzc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:14
 */
@Service
public class ShopCategoryServiceImpl extends ServiceImpl<ShopCategoryDao, ShopCategory> implements ShopCategoryService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ShopCategory> queryByParentId(Integer parentId) {
        return baseMapper.selectList(new EntityWrapper<ShopCategory>().eq("parent_id", parentId));
    }

    @Override
    public List<ShopCategory> queryRootCats() {
        List<ShopCategory> shopCategoryList = (List<ShopCategory>) redisTemplate.opsForList().rightPop(CommonConst.SHOPROOTCAT);
        if (shopCategoryList == null || shopCategoryList.size() == 0) {
            shopCategoryList = baseMapper.selectList(new EntityWrapper<ShopCategory>().eq("parent_id", 0));
            redisTemplate.opsForList().leftPush(CommonConst.SHOPROOTCAT, shopCategoryList);
        }
        return shopCategoryList;
    }
}
