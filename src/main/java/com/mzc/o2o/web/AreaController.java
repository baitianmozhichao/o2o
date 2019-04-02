package com.mzc.o2o.web;

import com.mzc.o2o.entity.Area;
import com.mzc.o2o.service.AreaService;
import com.mzc.o2o.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/30 21:25
 */
@RestController
@RequestMapping("/area")
@Slf4j
public class AreaController extends BaseController{

    @Autowired
    private AreaService areaService;

    @GetMapping("/areaList/{current}/{size}")
    public ResultVo areaList(@PathVariable("current")Integer current,@PathVariable("size")Integer size) {
        try {
            List<Area> areaList = areaService.queryList(current,size);
            return buildResultVo(areaList,areaList.size());
        } catch (Exception e) {
            return buildEmptyResultVo();
        }
    }

    @PostMapping("/addArea")
    public ResultVo addArea(@RequestBody Area area) {
        int effectRows = areaService.add(area);
        if(effectRows == 1){
            return buildResultVo(area, 1);
        }
        return buildEmptyResultVo();
    }

    @GetMapping("/addArea/{areaId}")
    public ResultVo queryById(@PathVariable("areaId") Integer areaId){
        return buildResultVo(areaService.query(areaId),1);
    }

}
