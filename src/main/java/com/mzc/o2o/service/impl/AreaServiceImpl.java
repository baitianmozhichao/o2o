package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.dao.AreaDao;
import com.mzc.o2o.entity.Area;
import com.mzc.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/30 21:11
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, Area> implements AreaService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Area> queryList() {
        List<Area> areaList = redisTemplate.opsForList().range(CommonConst.AREA, 0, -1);
        if (areaList == null || areaList.size() == 0) {
            areaList = baseMapper.selectList(null);
            redisTemplate.opsForList().leftPush(CommonConst.AREA, areaList);
        }
        return areaList;
    }
}
