package com.thd.springboot.framework.generator.core;

/**
 * com.thd.springboot.framework.generator.core.CodeGen
 *
 * @author: wanglei62
 * @DATE: 2020/3/23 10:23
 **/
public interface CodeGen {
    /**
     * 代码生成
     * @param tableName 表名
     * @throws Exception
     */
    public void generator(String tableName) throws Exception;

    /**
     * 根据template生成文件
     * @param tableName 表名
     * @param templatePath 模板文件路径
     * @param targetFolderPath 生成文件所在文件夹
     */
    public void fillData(String tableName,String templatePath,String targetFolderPath)  throws Exception;
}
