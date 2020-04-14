package com.thd.springboot.framework.generator.core.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

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
    @Value("${templateFolderPath}") // 模板目录位置
    private String templateFolderPath;
    @Value("${targetFolderPath}") // 文件生成目录位置
    private String targetFolderPath;
    @Autowired
    private Coding coding;
    @Autowired
    private CustomData customData;


}
