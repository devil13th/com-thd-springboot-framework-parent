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
public class Column extends GetterSetterBean{
    // 备注  例如:用户
    private String comment;

    // 是否主键
    private Boolean isPk = false;
    // 是否可以为空
    private Boolean isNullAble = true;
    // java数据类型
    private String dataType;
    // db数据类型
    private String dbDataType;

    // 字符串长度
    private Integer len;



    public Column(String name) {
        super(name);
    }

    public static Column createColumn(String name){
        Column column = new Column(name);
        return column;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
