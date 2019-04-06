package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.AreaDao;
import com.mzc.o2o.entity.Area;
import com.mzc.o2o.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/30 21:11
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, Area> implements AreaService {

    @Override
    public List<Area> queryList() {
        return baseMapper.selectList(null);
    }
}
