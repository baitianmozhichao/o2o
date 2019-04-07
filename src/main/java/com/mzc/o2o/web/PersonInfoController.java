package com.mzc.o2o.web;

import com.mzc.o2o.entity.PersonInfo;
import com.mzc.o2o.service.PersonInfoService;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/personInfo")
public class PersonInfoController extends BaseController {

    @Autowired
    private PersonInfoService personInfoService;

    /**
     * 获取所有个人信息,用于新增商铺信息时owner的选择
     * @return
     */
    @GetMapping("/getAll")
    public ResultVo<List<PersonInfo>> getAll() {
        ResultVo<List<PersonInfo>> resultVo = new ResultVo<List<PersonInfo>>();
        List<PersonInfo> personInfoList = personInfoService.selectList(null);
        buildResultVo(personInfoList,personInfoList.size());
        return resultVo;
    }

    /**
     * 根据名字获取个人信息
     * @param name
     * @return
     */
    @GetMapping("/findByName/{name}")
    public ResultVo<PersonInfo> findByName(@PathVariable(value = "name",required = false) String name){
        try {
            PersonInfo personInfo = personInfoService.findByName(name);
            return buildResultVo(personInfo,1);
        }catch (Exception e){
            return buildEmptyResultVo();
        }
    }
}