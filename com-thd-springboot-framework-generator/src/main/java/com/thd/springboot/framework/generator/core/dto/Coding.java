package com.thd.springboot.framework.generator.core.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * com.thd.springboot.framework.generator.dto.CodingInfo
 *
 * @author: wanglei62
 * @DATE: 2020/3/21 11:34
 **/
@Data
@Component
@ConfigurationProperties("coding")
public class Coding {
    // service 包名
    private String servicePackageName;
    // mapper 包名
    private String mapperPackageName;
    // controller 包名
    private String controllerPackageName;
    // entity 包名
    private String entityPackageName;

}
