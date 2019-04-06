package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mzc.o2o.dao.UserProductMapDao;
import com.mzc.o2o.entity.UserProductMap;
import com.mzc.o2o.service.UserProductMapService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/2 23:28
 */
@Service
public class UserProductMapServiceImpl extends ServiceImpl<UserProductMapDao,UserProductMap> implements UserProductMapService {
}
