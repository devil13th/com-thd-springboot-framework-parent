package com.thd.springboot.framework.generator.core;

import com.thd.springboot.framework.generator.core.dto.Table;
import javafx.scene.control.Tab;

/**
 * com.thd.springboot.framework.generator.core.CodeGen
 *
 * @author: wanglei62
 * @DATE: 2020/3/23 10:23
 **/
public interface CodeGen {
    /**
     * 生成表数据
     * @param tableName 表名
     * @throws Exception
     */
    public Table generator(String tableName) throws Exception;

    /**
     * 根据template生成文件
     * @param tableName 表名
     * @param templatePath 模板文件路径
     * @param targetFolderPath 生成文件所在文件夹
     */
    public void createCode(String tableName,String templatePath,String targetFolderPath)  throws Exception;
}
