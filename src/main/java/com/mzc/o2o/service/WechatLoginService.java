package com.mzc.o2o.service;

import com.mzc.o2o.entity.PersonInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: mzc
 */
public interface WechatLoginService {

    void registryPersonInfo(HttpServletRequest request, PersonInfo personInfo, String openId);
}
