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
public class Table {
    // 备注  例如:用户
    private String desc;
    // 表名  例如:sys_user
    private String name;
    // 表名 - 驼峰  例如:sysUser
    private String nameCamel;
    // 表明 - 大驼峰  例如:SysUser
    private String nameBigCamel;
    // 主键字段
    private Column pkColumn;
    // 表空间
    private String schema;
    // 全部字段集合
    private List<Column> allColumns;
    // 非主键字段集合
    private List<Column> normalColumns;

    public Table(){}
    public Table(String name) {
        this.name = name;
        this.nameCamel = ToCamelUtil.toCamel(name);
        this.nameBigCamel = ToCamelUtil.toBigCamel(name);
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
