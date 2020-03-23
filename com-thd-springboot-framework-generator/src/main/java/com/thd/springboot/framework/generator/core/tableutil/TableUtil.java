package com.thd.springboot.framework.generator.core.tableutil;


import com.thd.springboot.framework.generator.CodeGenColumnConfig;
import com.thd.springboot.framework.generator.core.dto.Table;

import java.util.List;

public interface TableUtil {
	/**
	 * 根据数据库表名加载列的配置
	 * @param tableName 表名
	 * @return
	 */
	public Table loadTable(String tableName) throws Exception;
	

}
