package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.PhoneAuthDao;
import com.mzc.o2o.entity.PhoneAuth;
import com.mzc.o2o.service.PhoneAuthService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:12
 */
@Service
public class PhoneAuthServiceImpl extends ServiceImpl<PhoneAuthDao,PhoneAuth> implements PhoneAuthService {
}