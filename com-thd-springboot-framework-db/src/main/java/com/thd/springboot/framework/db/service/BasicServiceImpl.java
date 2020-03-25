package com.thd.springboot.framework.db.service;

import com.thd.springboot.framework.db.entity.BasicEntity;
import com.thd.springboot.framework.db.mapper.BasicMapper;

import java.util.List;

/**
 * com.thd.springboot.framework.db.service.BasicServiceImpl
 *
 * @author: wanglei62
 * @DATE: 2020/3/25 8:51
 **/
public abstract class BasicServiceImpl<T extends BasicEntity> implements  BasicService<T>{

    public abstract BasicMapper<T> basicMapper();

    @Override
    public Integer add(T entity) {
        return basicMapper().add(entity);
    }

    @Override
    public Integer physicsDelete(Object id) {
        return basicMapper().physicsDelete(id);
    }

    @Override
    public Integer logicDelete(Object id) {
        return basicMapper().logicDelete(id);
    }

    @Override
    public Integer update(T entity) {
        return basicMapper().update(entity);
    }

    @Override
    public T queryById(Object id) {
        return basicMapper().queryById(id);
    }

    @Override
    public List<T> queryEq(T entity) {
        return basicMapper().queryEq(entity);
    }

    @Override
    public List<T> queryLike(T entity) {
        return basicMapper().queryLike(entity);
    }
}
