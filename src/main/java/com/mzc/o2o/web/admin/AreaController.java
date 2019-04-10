package com.mzc.o2o.web.admin;

import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.entity.Area;
import com.mzc.o2o.service.AreaService;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有区域
     * @return
     */
    @GetMapping("/getAll")
    public ResultVo getAll() {
        try {
            List<Area> areaList = areaService.queryList();
            return buildResultVo(areaList,areaList.size());
        } catch (Exception e) {
            return buildEmptyResultVo();
        }
    }

    /**
     * 新增区域
     * @param area
     * @return
     */
    @PostMapping("/addArea")
    public ResultVo addArea(Area area) {
        boolean result = areaService.insert(area);
        if(result){
            redisTemplate.opsForList().rightPop(CommonConst.AREA);
            return buildResultVo(area, 1);
        }
        return buildEmptyResultVo();
    }

    /**
     * 根据id查找区域
     * @param areaId
     * @return
     */
    @GetMapping("/queryById/{areaId}")
    public ResultVo queryById(@PathVariable("areaId") Integer areaId){
        return buildResultVo(areaService.selectById(areaId),1);
    }
}
