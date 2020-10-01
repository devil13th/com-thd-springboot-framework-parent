package com.thd.springboot.framework.db.service;

import com.github.pagehelper.PageInfo;
import com.thd.springboot.framework.entity.BasicEntity;

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
    Integer insert(T entity);

    /**
     * 删除实体,根据主键
     *
     * @param id
     * @return
     */
    Integer deletePhysicsById(Object id);

    /**
     * 逻辑删除,根据主键
     *
     * @param id
     * @return
     */
    Integer deleteLogicById(Object id);

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
     * 根据条件查询某一个实体
     *
     * @param entity 查询条件
     * @return
     */
    T queryByCondition(T entity);

    /**
     * 返回实体List,根据条件,精确匹配
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
     * 返回实体List,精确匹配且分页
     *
     * @param entity 实体
     * @return
     */
    PageInfo<T> queryListEqByPage(T entity);

    /**
     * 返回实体List,根据条件,模糊匹配且分页
     *
     * @param entity 实体
     * @return
     */
    PageInfo<T> queryListLikeByPage(T entity);


    /**
     * 批量新增
     * @param entityList
     */
    void insertBatch(List<T> entityList);
}
