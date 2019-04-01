package com.mzc.o2o.web;

import com.mzc.o2o.entity.Area;
import com.mzc.o2o.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/30 21:25
 */
@RestController
@RequestMapping("/area")
@Slf4j
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/areaList")
    public Map<String, Object> areaList() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            List<Area> areaList = areaService.getAllArea();
            modelMap.put("rows", areaList);
            modelMap.put("total", areaList.size());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    @PostMapping("/addArea")
    public Map<String, Object> addArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int effectRows = areaService.addArea(area);
        modelMap.put("rows", area);
        modelMap.put("total", effectRows);
        return modelMap;
    }

}
