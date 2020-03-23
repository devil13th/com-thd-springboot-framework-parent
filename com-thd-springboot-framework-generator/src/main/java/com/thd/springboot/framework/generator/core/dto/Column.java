package com.thd.springboot.framework.generator.core.dto;

import com.alibaba.fastjson.JSONObject;
import com.thd.springboot.framework.generator.core.tool.ToCamelUtil;
import lombok.Data;

/**
 * com.thd.springboot.framework.generator.dto.Column
 *
 * @author: wanglei62
 * @DATE: 2020/3/21 10:47
 **/
@Data
public class Column {
    // 备注  例如:用户
    private String desc;
    // 表名  例如:sys_user
    private String name;
    // 表名 - 驼峰  例如:sysUser
    private String nameCamel;
    // 表明 - 大驼峰  例如:SysUser
    private String nameBigCamel;
    // 是否主键
    private Boolean isPk = false;
    // 是否可以为空
    private Boolean isNullAble = true;
    // 数据类型
    private String dataType;
    public Column(){}
    public Column(String name) {
        this.name = name;
        this.nameCamel = ToCamelUtil.toCamel(name);
        this.nameBigCamel = ToCamelUtil.toBigCamel(name);
    }



    public static Column createColumn(String name){
        Column column = new Column(name);
        return column;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
