package com.mzc.o2o.service.impl;

import com.mzc.o2o.dao.AreaDao;
import com.mzc.o2o.entity.Area;
import com.mzc.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/30 21:11
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAllArea() {
        return areaDao.selectList(null);
    }

    @Override
    public Area getAreaById(Integer areaId) {
        return areaDao.selectById(areaId);
    }

    @Override
    public int addArea(Area area) {
        return areaDao.insert(area);
    }
}
