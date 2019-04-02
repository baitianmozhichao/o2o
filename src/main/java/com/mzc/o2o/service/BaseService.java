package com.mzc.o2o.service;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: mzc
 * @Date: 2019/4/2 20:42
 */
public interface BaseService<T> {

    int add(T t);

    int delete(Integer id);

    int update(T t);

    T query(Integer id);

    List<T> queryList();
}
