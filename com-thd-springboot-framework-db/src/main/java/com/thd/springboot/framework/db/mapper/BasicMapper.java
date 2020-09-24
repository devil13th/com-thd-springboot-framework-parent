package com.thd.springboot.framework.db.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thd.springboot.framework.entity.BasicEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * com.thd.springboot.framework.db.mapper.BasicMapper
 *
 * @author: wanglei62
 * @DATE: 2020/3/24 19:16
 **/
public interface BasicMapper<T extends BasicEntity>  {
    /**
     * 保存实体
     *
     * @param entity 实体
     * @return
     */
    Integer add(T entity);

    /**
     * 批量插入
     * @param entityList 实体列表
     */
    void insertBatch(List<T> entityList);

    /**
     * 删除实体,根据主键
     *
     * @param id
     * @return
     */
    Integer physicsDelete(@Param("id")Object id);

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


}
