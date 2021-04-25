package com.thd.springboot.framework.db.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thd.springboot.framework.entity.BasicEntity;
import org.apache.ibatis.annotations.MapKey;
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
    Integer insert(T entity);

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
    Integer deletePhysicsById(@Param("id")Object id);

    /**
     * 逻辑删除,根据主键
     *
     * @param id
     * @return
     */
    Integer deleteLogicById(Object id);


    /**
     * 根据条件逻辑删除
     * @param entity 条件
     * @return
     */
    Integer deleteLogicByCondition(T entity);

    /**
     * 批量逻辑删除,根据主键
     *
     * @param idList 主键集合
     * @return
     */
    Integer deleteLogicByIds(@Param("idList")List<Object> idList);

    /**
     * 更新实体,根据主键
     *
     * @param entity 实体
     * @return
     */
    Integer update(T entity);

    /**
     * 批量更新
     * @param targetBean 要更新的值
     * @param conditionBean 更新的条件
     */
    void updateBatch(@Param(value="targetBean") T targetBean,@Param(value="conditionBean") T conditionBean);

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
     * 返回实体List,根据条件
     *
     * @param entity 实体
     * @return
     */
    List<T> queryListEq(T entity);

    /**
     * 返回实体List,根据条件,模糊匹配
     *
     * @param entity 实体
     * @return
     */
    List<T> queryListLike(T entity);





}
