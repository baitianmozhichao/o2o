package com.mzc.o2o.service;

import com.mzc.o2o.entity.Area;

import java.util.List;

public interface AreaService extends BaseService<Area>{

    List<Area> queryList(Integer current, Integer size);
}