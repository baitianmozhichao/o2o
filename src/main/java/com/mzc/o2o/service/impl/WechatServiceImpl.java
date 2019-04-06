package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.WechatAuthDao;
import com.mzc.o2o.entity.WechatAuth;
import com.mzc.o2o.service.WechatAuthService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 17:52
 */
@Service
public class WechatServiceImpl extends ServiceImpl<WechatAuthDao,WechatAuth> implements WechatAuthService {
}