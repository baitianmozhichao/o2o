package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.entity.Award;
import com.mzc.o2o.service.AwardService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 17:16
 */
@Service
public class AwardServiceImpl extends BaseServiceImpl<Award> implements AwardService {
    @Override
    public List<Award> findByShopId(Integer shopId) {
        return dao.selectList(new EntityWrapper<Award>().eq("shop_id",shopId));
    }
}
