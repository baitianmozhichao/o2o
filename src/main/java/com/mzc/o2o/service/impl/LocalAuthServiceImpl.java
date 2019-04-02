package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.entity.LocalAuth;
import com.mzc.o2o.exception.LocalAuthException;
import com.mzc.o2o.service.LocalAuthService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 17:43
 */
@Service
public class LocalAuthServiceImpl extends BaseServiceImpl<LocalAuth> implements LocalAuthService {

    @Override
    public LocalAuth findByUserId(Integer userId) {
        List<LocalAuth> localAuthList = dao.selectList(new EntityWrapper<LocalAuth>().eq("user_id",userId));
        if(localAuthList.size()==0){
            throw new LocalAuthException("["+userId+"]不存在localAuth账号");
        }
        return localAuthList.get(0);
    }
}
