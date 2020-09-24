package com.thd.springboot.framework.db.service;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.db.mapper.BasicMapper;
import com.thd.springboot.framework.entity.BasicEntity;
//import com.thd.springboot.framework.db.mapper.BasicMapper;
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
    public Integer insert(T entity) {
        if(entity.getCreateTime() == null){
            entity.setCreateTime(new Date());
        }
        if(entity.getModifyTime() == null){
            entity.setModifyTime(new Date());
        }
        entity.setIsDeleted(0);
        int result =  basicMapper().insert(entity);
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
    public Integer deletePhysics(Object id) {
        return basicMapper().deletePhysics(id);
    }

    @Override
    public Integer deleteLogic(Object id) {
        T entity = this.queryById(id);
        entity.setModifyTime(new Date());
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
    public T queryByCondition(T entity) {
        return basicMapper().queryByCondition(entity);
    }

    @Override
    public List<T> queryEq(T entity) {
        return basicMapper().queryListEq(entity);
    }

    @Override
    public List<T> queryLike(T entity) {
        return basicMapper().queryListLike(entity);
    }

    @Override
    public PageInfo<T> queryListEqByPage(T entity) {
        PageUtils.setPageHelper(entity);
        PageInfo<T> pager = new PageInfo<T>(basicMapper().queryListEq(entity));
        return pager;
    }

    @Override
    public PageInfo<T> queryListLikeByPage(T entity) {
        PageUtils.setPageHelper(entity);
        PageInfo<T> pager = new PageInfo<T>(basicMapper().queryListLike(entity));
        return pager;
    }




}
