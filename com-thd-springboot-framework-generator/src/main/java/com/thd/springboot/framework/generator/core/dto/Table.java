package com.thd.springboot.framework.generator.core.dto;

import com.alibaba.fastjson.JSONObject;
import com.thd.springboot.framework.generator.core.tool.ToCamelUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * com.thd.springboot.framework.generator.dto.Table
 *
 * @author: wanglei62
 * @DATE: 2020/3/21 10:41
 **/
@Data
public class Table  extends GetterSetterBean{
    // 备注  例如:用户
    private String comment;
    // 主键字段
    private Column pkColumn;
    // 表空间
    private String schema;
    // 全部字段集合
    private List<Column> allColumns;
    // 非主键字段集合
    private List<Column> normalColumns;

    public Table(String name) {
        super(name);
    }

    public Table(String name,String schema){
        this(name);
        this.schema = schema;
    }

    public static Table createTable(String name,String schema){
        Table table = new Table(name,schema);
        return table;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
