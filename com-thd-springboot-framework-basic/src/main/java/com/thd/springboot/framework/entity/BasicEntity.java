package com.thd.springboot.framework.entity;

//import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 public class BasicEntity<T> extends MyPage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty("ID")
    // @Field("id")
//    @TableField(exist = false)
    private T id;

    /**
     * 是否已删除
     */
    @ApiModelProperty("是否已删除")
    @JsonIgnore
//    @TableField(exist = false)
    private Integer isDeleted = 0;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
//    @TableField(exist = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
//    @TableField(exist = false)
    private Date modifyTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
//    @TableField(exist = false)
    private String createBy;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
//    @TableField(exist = false)
    private String modifyBy;

    /**
     * 通用开始日期
     */
    @ApiModelProperty("通用开始日期")
//    @TableField(exist = false)
    private Date startDate;

    /**
     * 通用结束日期
     */
    @ApiModelProperty("通用结束日期")
//    @TableField(exist = false)
    private Date endDate;
}
