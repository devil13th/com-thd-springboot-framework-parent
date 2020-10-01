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

    public abstract BasicMapper<T> getMapper();

    @Override
    public Integer insert(T entity) {
        if(entity.getCreateTime() == null){
            entity.setCreateTime(new Date());
        }
        if(entity.getModifyTime() == null){
            entity.setModifyTime(new Date());
        }
        entity.setIsDeleted(0);
        int result =  getMapper().insert(entity);
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
        getMapper().insertBatch(entityList);
    }

    @Override
    public Integer deletePhysicsById(Object id) {
        return getMapper().deletePhysicsById(id);
    }

    @Override
    public Integer deleteLogicById(Object id) {
        T entity = this.queryById(id);
        entity.setModifyTime(new Date());
        entity.setIsDeleted(1);
        return this.getMapper().update(entity);
    }

    @Override
    public Integer update(T entity) {
        if(entity.getModifyTime() == null){
            entity.setModifyTime(new Date());
        }
        return getMapper().update(entity);
    }

    @Override
    public T queryById(Object id) {
        return getMapper().queryById(id);
    }

    @Override
    public T queryByCondition(T entity) {
        return getMapper().queryByCondition(entity);
    }

    @Override
    public List<T> queryEq(T entity) {
        return getMapper().queryListEq(entity);
    }

    @Override
    public List<T> queryLike(T entity) {
        return getMapper().queryListLike(entity);
    }

    @Override
    public PageInfo<T> queryListEqByPage(T entity) {
        PageUtils.setPageHelper(entity);
        PageInfo<T> pager = new PageInfo<T>(getMapper().queryListEq(entity));
        return pager;
    }

    @Override
    public PageInfo<T> queryListLikeByPage(T entity) {
        PageUtils.setPageHelper(entity);
        PageInfo<T> pager = new PageInfo<T>(getMapper().queryListLike(entity));
        return pager;
    }




}
