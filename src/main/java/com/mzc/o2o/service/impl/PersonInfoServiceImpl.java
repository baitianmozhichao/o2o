package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.PersonInfoDao;
import com.mzc.o2o.entity.PersonInfo;
import com.mzc.o2o.service.PersonInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 17:27
 */
@Service
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoDao,PersonInfo> implements PersonInfoService {
    @Override
    public PersonInfo findByName(String name) {
        List<PersonInfo> personInfoList = baseMapper.selectList(new EntityWrapper<PersonInfo>().eq("name",name));
        if(personInfoList==null || personInfoList.size()==0){
            throw new RuntimeException("不存在该名字的用户");
        }
        if(personInfoList.size()>1){
            throw new RuntimeException("存在多条该名字的用户，请联系管理员，电话：18826139502");
        }
        return personInfoList.get(0);
    }
}
