package com.mzc.o2o.service;

import com.mzc.o2o.entity.Area;

import java.util.List;

public interface AreaService {

	List<Area> getAllArea();

	Area getAreaById(Integer areaId);

	int addArea(Area area);
}
