package com.thd.springboot.framework.generator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.thd.springboot.framework.generator.core.CodeGen;
import com.thd.springboot.framework.generator.core.dto.CodeGenConfig;
import com.thd.springboot.framework.generator.core.tableutil.MysqlTableUtilImpl;
import com.thd.springboot.framework.generator.core.tableutil.TableUtil;
import com.thd.springboot.framework.generator.core.tool.CodeGenUtil;
import com.thd.springboot.framework.generator.core.tool.ToCamelUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.stream.Stream;

/**
 * com.thd.springboot.framework.generator.GeneratorApplication
 *
 * @author: wanglei62
 * @DATE: 2020/3/21 23:06
 **/
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(GeneratorApplication.class, args);
        String[] names = ctx.getBeanDefinitionNames();
       // Stream.of(names).forEach(System.out::println);

//        JdbcTemplate j = ctx.getBean(JdbcTemplate.class);
//        System.out.println(j);





        CodeGen tu = ctx.getBean(CodeGenUtil.class);
//        System.out.println(tu);

        tu.generator("sys_user");
        tu.fillData("sys_user","D:\\work\\java\\com-thd-springboot-framework-parent\\com-thd-springboot-framework-generator\\src\\main\\resources\\template\\mybatis\\example.ftl","D:\\deleteme\\cg\\a.txt");
    }
}
