package com.mzc.o2o.service;

import com.baomidou.mybatisplus.service.IService;
import com.mzc.o2o.entity.PersonInfo;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 17:24
 */
public interface PersonInfoService extends IService<PersonInfo> {

    PersonInfo findByName(String name);
}
