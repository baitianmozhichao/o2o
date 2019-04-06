package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.Area;

import java.util.List;

public interface AreaService extends IService<Area> {

    List<Area> queryList();
}