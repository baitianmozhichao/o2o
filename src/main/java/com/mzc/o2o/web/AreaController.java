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

import java.io.BufferedReader;
import java.io.FileReader;
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

    @GetMapping("/getAll")
    public ResultVo getAll() {
        try {
            List<Area> areaList = areaService.queryList();
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("E:/2.txt"));
        String line;
        StringBuilder result = new StringBuilder();
//      Integer agentTel = vehInfVo.getAgentTel();
        while ((line = br.readLine()) != null) {
            char[] cs = line.toCharArray();
            cs[0]+=32;
            System.out.println("Integer "+String.valueOf(cs)+" = "+"vehInfVo.get"+line+"();");
        }
        br.close();
        System.out.println(result.toString());
    }
}
