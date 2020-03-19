package com.thd.springboot.framework.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * com.thd.springboot.framework.db.entity.BaseEntity
 *
 * @author: wanglei62
 * @DATE: 2020/1/21 17:51
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
// public class BasicEntity<T> extends Page implements Serializable {
public class BasicEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty("ID")
    // @Field("id")
    private T id;

    /**
     * 是否已删除
     */
    @ApiModelProperty("是否已删除")
    @JsonIgnore
    private Integer isDeleted = 0;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date modifyTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String modifyBy;

    /**
     * 通用开始日期
     */
    @ApiModelProperty("通用开始日期")
    private Date startDate;

    /**
     * 通用结束日期
     */
    @ApiModelProperty("通用结束日期")
    private Date endDate;
}
