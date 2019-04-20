package com.mzc.o2o.service;

import com.mzc.o2o.entity.PersonInfo;

/**
 * @auther: mzc
 */
public interface WechatLoginService {

    void registryPersonInfo(PersonInfo personInfo, String openId);
}
