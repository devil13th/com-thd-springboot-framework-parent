package com.thd.springboot.framework.generator.core.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * com.thd.springboot.framework.generator.core.dto.CustomData
 *
 * @author: wanglei62
 * @DATE: 2020/4/14 9:28
 **/
@Data
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomData {
    private Map<String,String> map;
}
