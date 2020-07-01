package com.thd.springboot.framework.generator.core.tool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * com.thd.springboot.framework.generator.core.tool.StringTemplate
 *
 * @author: wanglei62
 * @DATE: 2020/3/24 11:17
 **/
public class StringTemplate {
    /**
     * 解析字符串模板,通用方法
     *
     * @param template
     *            字符串模板
     * @param model;
     *            数据
     * @param configuration
     *            配置
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model, Configuration configuration)
            throws IOException, TemplateException {
        if (template == null) {
            return null;
        }
        if (configuration == null) {
            configuration = new Configuration();
        }
        StringWriter out = new StringWriter();
        new Template("template", new StringReader(template), configuration).process(model, out);
        return out.toString();
    }

    /**
     * 根据字符串和数据直接生成字符串
     * @param data 数据
     * @param templateStr 字符串模板
     * @return
     * @throws Exception
     */
    public static String generatByTemplateStr(String templateStr,Object data) throws Exception{
        Logger logger = LoggerFactory.getLogger(StringTemplate.class);

        Configuration configuration = new Configuration();
        String charset = "utf-8";

        StringWriter out = new StringWriter();
        new Template("template", new StringReader(templateStr.toString()), configuration).process(data, out);
        return out.toString();
    }

    /**
     * 根据模板和数据生成字符串
     * @param data 数据
     * @param templatePath 模板文件位置
     * @return
     * @throws Exception
     */
    public static String generatByTemplatePath(Object data,String templatePath) throws Exception{
        Logger logger = LoggerFactory.getLogger(StringTemplate.class);

        Configuration configuration = new Configuration();
        String charset = "utf-8";
        File templateFile = new File(templatePath);
        File templateFolder = templateFile.getParentFile();
        BufferedReader reader = null;
        StringBuffer templateStr = new StringBuffer();

        try{
            reader = new BufferedReader(new FileReader(templatePath));
            String tempStr ;
            while( (tempStr = reader.readLine()) != null){
                templateStr.append(tempStr);
            }
            reader.close();
        }catch(Exception e){
            throw new RuntimeException("read freemarker template err : [" + templatePath + "]");
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }


        logger.info("模板:" + templateFile.getAbsolutePath());
        StringWriter out = new StringWriter();
        new Template("template", new StringReader(templateStr.toString()), configuration).process(data, out);
        return out.toString();
    }


//    public static void main(String[] args) throws IOException, TemplateException{
//        String StrTemplate = "姓名：${name}；年龄：${age}"; // 测试模板数据（一般存储在数据库中）
//        Map<String,Object> map = new HashMap<String,Object>();  // map，需要动态填充的数据
//        map.put("name", "张三");
//        map.put("age", "25");
//        String resultStr = process(StrTemplate, map, null); // 解析字符串模板的方法，并返回处理后的字符串
//        System.out.println(resultStr);
//    }

}
