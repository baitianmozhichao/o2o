package com.mzc.o2o.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mzc.o2o.service.BaseService;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: mzc
 * @Date: 2019/4/2 20:45
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    BaseMapper<T> dao;

    @Override
    public int add(T t) {
        return dao.insert(t);
    }

    @Override
    public int delete(Integer id) {
        return dao.deleteById(id);
    }

    @Override
    public int update(T t) {
        return dao.updateById(t);
    }

    @Override
    public T query(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public List<T> queryList() {
        return dao.selectList(null);
    }
}
