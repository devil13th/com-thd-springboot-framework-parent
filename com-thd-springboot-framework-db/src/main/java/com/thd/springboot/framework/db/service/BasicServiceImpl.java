package com.thd.springboot.framework.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.db.entity.BasicEntity;
import com.thd.springboot.framework.db.mapper.BasicMapper;
import com.thd.springboot.framework.db.utils.PageUtils;

import java.util.Date;
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
        if(entity.getCreateTime() == null){
            entity.setCreateTime(new Date());
        }
        if(entity.getModifyTime() == null){
            entity.setModifyTime(new Date());
        }
        entity.setIsDeleted(0);
        int result =  basicMapper().add(entity);
        if(result != 1){
            throw new RuntimeException(" add failed ");
        }
        return result;
    }

    @Override
    public void insertBatch(List<T> entityList) {
        entityList.stream().forEach(entity -> {
            if(entity.getCreateTime() == null){
                entity.setCreateTime(new Date());
            }
            if(entity.getModifyTime() == null){
                entity.setModifyTime(new Date());
            }
            entity.setIsDeleted(0);
        });
        basicMapper().insertBatch(entityList);
    }

    @Override
    public Integer physicsDelete(Object id) {
        return basicMapper().physicsDelete(id);
    }

    @Override
    public Integer logicDelete(Object id) {
        T entity = this.queryById(id);
        entity.setIsDeleted(1);
        return this.basicMapper().update(entity);
    }

    @Override
    public Integer update(T entity) {
        if(entity.getModifyTime() == null){
            entity.setModifyTime(new Date());
        }
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

    @Override
    public PageInfo<T> queryEqByPage(T entity) {
        PageUtils.setPageHelper(entity);
        PageInfo<T> pager = new PageInfo<T>(basicMapper().queryEq(entity));
        return pager;
    }

    @Override
    public PageInfo<T> queryLikeByPage(T entity) {
        PageUtils.setPageHelper(entity);
        PageInfo<T> pager = new PageInfo<T>(basicMapper().queryLike(entity));
        return pager;
    }

    @Override
    public List<T> queryByWrapper(QueryWrapper<T> queryWrapper) {
        queryWrapper.eq("is_deleted","0");
        return basicMapper().selectList(queryWrapper);
    }


}
