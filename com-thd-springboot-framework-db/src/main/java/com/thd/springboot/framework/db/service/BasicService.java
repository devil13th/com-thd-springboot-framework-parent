package com.thd.springboot.framework.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.db.entity.BasicEntity;

import java.util.List;

/**
 * com.thd.springboot.framework.db.service.BasicService
 *
 * @author: wanglei62
 * @DATE: 2020/3/25 8:47
 **/
public interface BasicService<T extends BasicEntity>  {
    /**
     * 保存实体
     *
     * @param entity 实体
     * @return
     */
    Integer add(T entity);

    /**
     * 删除实体,根据主键
     *
     * @param id
     * @return
     */
    Integer physicsDelete(Object id);

    /**
     * 逻辑删除,根据主键
     *
     * @param id
     * @return
     */
    Integer logicDelete(Object id);

    /**
     * 更新实体,根据主键
     *
     * @param entity 实体
     * @return
     */
    Integer update(T entity);

    /**
     * 返回实体,根据主键
     *
     * @param id 主键
     * @return
     */
    T queryById(Object id);

    /**
     * 返回实体List,根据条件
     *
     * @param entity 实体
     * @return
     */
    List<T> queryEq(T entity);

    /**
     * 返回实体List,根据条件,模糊匹配
     *
     * @param entity 实体
     * @return
     */
    List<T> queryLike(T entity);

    /**
     * 返回实体List,根据条件
     *
     * @param entity 实体
     * @return
     */
    PageInfo<T> queryEqByPage(T entity);

    /**
     * 返回实体List,根据条件,模糊匹配
     *
     * @param entity 实体
     * @return
     */
    PageInfo<T> queryLikeByPage(T entity);

    List<T> queryByWrapper(QueryWrapper<T> wrapper);
}
