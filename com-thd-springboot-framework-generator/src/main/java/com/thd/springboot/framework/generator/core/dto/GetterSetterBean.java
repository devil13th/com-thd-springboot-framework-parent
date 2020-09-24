package com.thd.springboot.framework.generator.core.dto;

import com.thd.springboot.framework.generator.core.tool.ToCamelUtil;


public class GetterSetterBean {
    // 表名  例如:sys_user
    private String name;
    // 全小写 例如:sysuser
    private String nameForPackage;
    // 表名 - 驼峰  例如:sysUser
    private String nameCamel;
    // 表名 - 大驼峰  例如:SysUser
    private String nameBigCamel;
    public GetterSetterBean(String name){
        this.name = name;
        this.nameCamel = ToCamelUtil.toCamel(name);
        this.nameBigCamel = ToCamelUtil.toBigCamel(name);
        this.nameForPackage = ToCamelUtil.toCamel(name).toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
        this.nameCamel = ToCamelUtil.toCamel(name);
        this.nameBigCamel = ToCamelUtil.toBigCamel(name);
        this.nameForPackage =  ToCamelUtil.toCamel(name).toLowerCase();
    }

    public String getGetter(){
        return "get" + this.nameBigCamel;
    }

    public String getSetter(){
        return "set" + this.nameBigCamel;
    }

    public String getName() {
        return name;
    }



    public String getNameCamel() {
        return nameCamel;
    }

    public String getNameBigCamel() {
        return nameBigCamel;
    }

    public String getNameForPackage() {return nameForPackage;}
}
