package com.thd.springboot.framework.generator.configuration;

import com.thd.springboot.framework.generator.core.dto.Db;
import com.thd.springboot.framework.generator.core.dto.TemplateData;
import com.thd.springboot.framework.generator.core.tableutil.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.Driver;

/**
 * com.thd.springboot.framework.generator.configuration.CodeGenConfiguration
 *
 * @author: wanglei62
 * @DATE: 2020/3/22 0:00
 **/
@Configuration
public class CodeGenConfiguration {
    @Autowired
    private TemplateData templateData;

    @Bean
    public TableUtil tableUtil(){
        if(templateData.getDbType().equalsIgnoreCase("mysql")){
            return new MysqlTableUtilImpl();
        }else if(templateData.getDbType().equalsIgnoreCase("oracle")){
            return new OracleTableUtilImpl();
        }else if(templateData.getDbType().equalsIgnoreCase("greenplum")){
            return new GreenplumTableUtilImpl();
        }else if(templateData.getDbType().equalsIgnoreCase("pgsql")){
            return new PgsqlTableUtilImpl();
        }else{
            throw new RuntimeException(" illegal dbtype ");
        }
    }

}
