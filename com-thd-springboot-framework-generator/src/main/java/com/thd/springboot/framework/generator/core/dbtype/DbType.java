package com.thd.springboot.framework.generator.core.dbtype;

/**
 * com.thd.springboot.framework.generator.core.dbtype.DbType
 *
 * @author: wanglei62
 * @DATE: 2020/3/21 23:43
 **/
public enum DbType {
    MYSQL("MYSQL"),
    ORACLE("ORACLE"),
    SQLSERVER("SQLSERVER"),
    PGSQL("PGSQL"),
    GREENPLUM("GREENPLUM");
    private String dbTypeName;
    DbType(String dbTypeName){
        this.dbTypeName = dbTypeName;
    }


}
