package com.thd.springboot.framework.entity;

//import com.alibaba.fastjson.annotation.JSONField;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thd.springboot.framework.utils.PropertyUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * com.thd.springboot.framework.model.Page
 *
 * @author: wanglei62
 * @DATE: 2020/1/21 17:28
 **/
@Data
public class MyPage implements Serializable {
    @ApiModelProperty("页码，从1开始")
//    @TableField(exist = false)
    private Integer pageNum;
    @ApiModelProperty("每页大小")
//    @JSONField(serialize = false)
//    @TableField(exist = false)
    private Integer pageSize;
    @ApiModelProperty("排序字段")
//    @JSONField(serialize = false)
//    @TableField(exist = false)
    private String sortField;
    @ApiModelProperty("排序方式")
//    @JSONField(serialize = false)
//    @TableField(exist = false)
    private String sortOrder;
    @ApiModelProperty("数据权限sql")
//    @JSONField(serialize = false)
    @JsonIgnore
//    @TableField(exist = false)
    private String dataSql;

//    @TableField(exist = false)
    private String orderBy;

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        String orderBy = null;
        if (StringUtils.isNotBlank(this.sortField)) {
            orderBy = PropertyUtils.camel2Underline(this.sortField);
        }

        if (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(this.sortOrder)) {
            orderBy = orderBy + " " + this.sortOrder;
        }

        return orderBy;
    }

    public MyPage() {
    }



    public String toString() {
        return "Page(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ", sortField=" + this.getSortField() + ", sortOrder=" + this.getSortOrder() + ", dataSql=" + this.getDataSql() + ")";
    }

}
