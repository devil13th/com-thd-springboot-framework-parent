package com.thd.springboot.framework.generator.core.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * com.thd.springboot.framework.generator.core.dto.TemplateData
 *
 * @author: wanglei62
 * @DATE: 2020/3/23 10:05
 **/
@Data
@Component
public class TemplateData {
    private Table table;
    @Autowired
    private Db db;
    @Value("MYSQL") // 默认 MYSQL
    private String dbType;
    @Autowired
    private Coding coding;

}
