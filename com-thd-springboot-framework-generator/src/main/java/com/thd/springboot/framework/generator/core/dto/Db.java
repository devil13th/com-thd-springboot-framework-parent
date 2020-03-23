package com.thd.springboot.framework.generator.core.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * com.thd.springboot.framework.generator.dto.DbInfo
 * 数据库连接信息
 * @author: wanglei62
 * @DATE: 2020/3/21 11:31
 **/
@Data
@Component
@ConfigurationProperties("db")
public class Db {
    private String driver;
    private String url;
    private String user;
    private String password;
    private String dbType;
    private String schema;
}
