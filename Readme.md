[TOC]

# 框架使用说明

# 框架一览

## 框架结构说明

## 依赖说明

## 特征



# 项目搭建

## 项目模块结构

### web模块

### starter模块



## 项目总体依赖



## 模块依赖

### Web

1. 添加依赖

   在`项目web模块`中添加框架中的`框架web模块`依赖
   
   ```
   <dependency>
       <groupId>com.thd.springboot.framework</groupId>
       <artifactId>com-thd-springboot-framework-web</artifactId>
       <version>1.0.0-SNAPSHOT</version>
</dependency>
   ```
   
2. 在`项目starter模块`的application.yml中配置端口和项目根

   ```
   server:
     port: 8899
     servlet:
       context-path: /thd
   ```

3. 添加Controller

   ```
   @Controller
   @RequestMapping("/...")
   public class ExampleController extends BasicController {
       @ResponseBody
       @RequestMapping("/test")
       // url : http://127.0.0.1:8899/thd/example/test
       public Message testLog(){
           this.getLogger().info("test()");
           this.getLogger().debug("debug");
           this.getLogger().error("error");
           this.getLogger().warn("warn");
           return Message.success();
       }
   }
   ```

4. `项目starter模块`的启动类加注释进行扫描配置

   ```
   @SpringBootApplication(scanBasePackages = "com.thd")
   public class ApplicationServer{
   	...
   }
   ```

   

### Db

1. `项目starter模块`的application.yml中添加数据源和mybatis的配置

   ```
   spring:
     ############################ Db ############################
     datasource:
       # 数据库的url、登录名、密码和数据库名
       url: jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&zeroDateTimeBehavior=convertToNull
       username: root
       password: 123456
       # 使用阿里的Druid连接池
       type: com.alibaba.druid.pool.DruidDataSource
       driver-class-name: com.mysql.cj.jdbc.Driver
       # druid:
       # 连接池的配置信息
       # 初始化大小，最小，最大
       initialSize: 5
       minIdle: 5
       maxActive: 20
       # 配置获取连接等待超时的时间
       maxWait: 60000
       # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
       timeBetweenEvictionRunsMillis: 60000
       # 配置一个连接在池中最小生存的时间，单位是毫秒
       minEvictableIdleTimeMillis: 300000
       validationQuery: SELECT 1 FROM DUAL
       testWhileIdle: true
       testOnBorrow: false
       testOnReturn: false
       poolPreparedStatements: true
       # 打开PSCache，并且指定每个连接上PSCache的大小
       maxPoolPreparedStatementPerConnectionSize: 20
       # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
       filters: stat,wall
       # 通过connectProperties属性来打开mergeSql功能；慢SQL记录 单位毫秒
       connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=50
       # 配置监控服务器
       stat-view-servlet:
         login-username: admin
         login-password: 123456
         reset-enable: false
         url-pattern: /druid/*
         # 添加IP白名单
         #allow:
         # 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
         #deny:
       web-stat-filter:
         # 添加过滤规则
         url-pattern: /*
         # 忽略过滤格式
         exclusions: "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"
     ############################ Db - mybatis ############################
   
   mybatis:
     mapper-locations: classpath:com/thd/springboot/framework/example/mapper/*.xml #这里是mapper的配置文件
     # 这里是实体类的包 添加这个配置后就不用在mapper.xml配置文件中aliases了
     type-aliases-package: com.thd.springboot.framework.example.entity
     configuration:
       map-underscore-to-camel-case: true # 驼峰属性与数据库字段下划线自动转换
   ```

   

### Redis

### Log

#### 说明

日志框架使用Logback，也是Springboot 默认的日志框架，从依赖中可以看出

```
spring-boot-dependencies
 |- spring-boot-starter
     |- spring-boot-starter-logging
         |- logback-classic
         |- log4j-to-slf4j
         |- jul-to-slf4j
```

所以只要使用Springboot框架，Logback的依赖自动加到了项目中

#### 使用

以下内容均在`项目starter模块`中

1. applicatoin.yml 中配置logback-spring.xml的位置和日志保存的位置

    ```
    logging:
      config: classpath:config/logback-spring.xml
      path: D:/deleteme/logbacks
	```

2. 添加logback-spring.xml

   将logback-spring.xml放到resources/config目录下

3. logback-spring.xml内容

   参见com-thd-springboot-framework-example-starter项目下的logback-spring.xml



