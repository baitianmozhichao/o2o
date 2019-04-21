package com.mzc.o2o.web.admin;

import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.entity.PersonInfo;
import com.mzc.o2o.service.PersonInfoService;
import com.mzc.o2o.util.BindingResultUtil;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/personInfo")
public class PersonInfoController extends BaseController {

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取所有个人信息,用于新增商铺信息时owner的选择
     *
     * @return
     */
    @GetMapping("/getAll")
    public ResultVo<List<PersonInfo>> getAll() {
        List<PersonInfo> personInfoList = redisTemplate.opsForList().range(CommonConst.PERSONINFO, 0, -1);
        if (personInfoList == null || personInfoList.size() == 0) {
            personInfoList = personInfoService.selectList(null);
            redisTemplate.opsForList().leftPush(CommonConst.PERSONINFO, personInfoList);
        }
        return buildResultVo(personInfoList, personInfoList.size());
    }

    /**
     * 根据名字获取个人信息
     *
     * @param name
     * @return
     */
    @GetMapping("/findByName/{name}")
    public ResultVo<PersonInfo> findByName(@PathVariable(value = "name", required = false) String name) {
        try {
            PersonInfo personInfo = personInfoService.findByName(name);
            return buildResultVo(personInfo, 1);
        } catch (Exception e) {
            return buildEmptyResultVo();
        }
    }

    /**
     * 新增个人信息：用户注册
     * @param personInfo
     * @return
     */
    @PostMapping("/addPersonInfo")
    public ResultVo<PersonInfo> addPersonInfo(@Valid PersonInfo personInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return buildFailResultVo(BindingResultUtil.bindResult2Str(bindingResult),0);
        }
        boolean res = personInfoService.insert(personInfo);
        if(res){
            redisTemplate.opsForList().rightPop(CommonConst.PERSONINFO);
            return buildResultVo(personInfo, 1);
        }
        return buildEmptyResultVo();
    }

    /**
     * 获取当前账号信息
     * @param request
     * @return
     */
    @GetMapping("/getCurrentPersonInfo")
    public PersonInfo getCurrentPersonInfo(HttpServletRequest request){
        PersonInfo personInfo = (PersonInfo)request.getSession().getAttribute("personInfo");
        return personInfo;
    }

    /**
     * 更新个人信息
     * @param request
     * @param personInfo
     * @return
     */
    @PostMapping("/updatePersonInfo")
    public ResultVo<Boolean> updatePersonInfo(HttpServletRequest request, PersonInfo personInfo){
        PersonInfo pi = (PersonInfo)request.getSession().getAttribute("personInfo");
        personInfo.setUserId(pi.getUserId());
        personInfoService.updateById(personInfo);
        request.getSession().setAttribute("personInfo",personInfo);
        return buildResultVo(true,1);
    }
}