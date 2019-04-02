package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
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
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {
    @Override
    public List<Area> queryList(Integer current, Integer size) {
        return dao.selectPage(new Page<>(current,size),null);
    }
}
