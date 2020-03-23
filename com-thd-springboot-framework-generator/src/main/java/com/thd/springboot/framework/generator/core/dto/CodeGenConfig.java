package com.thd.springboot.framework.generator.core.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * com.thd.springboot.framework.generator.dto.DataDto
 *
 * @author: wanglei62
 * @DATE: 2020/3/21 11:31
 **/
@Data
@ConfigurationProperties("CodeGenConfig")
public class CodeGenConfig {
    @Value("MYSQL") // 默认 MYSQL
    private String dbType;
}
