package com.mzc.o2o.service.impl;

import com.mzc.o2o.dao.PersonInfoDao;
import com.mzc.o2o.dao.WechatAuthDao;
import com.mzc.o2o.entity.PersonInfo;
import com.mzc.o2o.entity.WechatAuth;
import com.mzc.o2o.service.WechatLoginService;
import com.mzc.o2o.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @auther: mzc
 */
@Transactional
@Service
public class WechatLoginServiceImpl implements WechatLoginService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Override
    public void registryPersonInfo(PersonInfo personInfo, String openId) {
        try {
//        使用uuid生成八位长度的useId
            String userId = UUIDUtil.get8UUID();
            personInfo.setUserId(userId);
            personInfoDao.insert(personInfo);
            WechatAuth wechatAuth = new WechatAuth();
            wechatAuth.setOpenId(openId);
            wechatAuth.setUserId(userId);
            wechatAuth.setCreateTime(new Date());
            wechatAuthDao.insert(wechatAuth);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("关联账号信息失败");
        }
    }
}
