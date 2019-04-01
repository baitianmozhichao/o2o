package com.mzc.o2o.service;

import com.mzc.o2o.BaseTest;
import com.mzc.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/30 21:15
 */
public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void getAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("北苑",areaList.get(0).getAreaName());
    }
}