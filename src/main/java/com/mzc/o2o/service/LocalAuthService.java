package com.mzc.o2o.service;

import com.mzc.o2o.entity.LocalAuth;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 17:41
 */
public interface LocalAuthService extends BaseService<LocalAuth>{
    LocalAuth findByUserId(Integer userId);
}
