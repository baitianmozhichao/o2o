package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.Award;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 17:15
 */
public interface AwardService extends IService<Award> {

    List<Award> findByShopId(Integer shopId);
}
