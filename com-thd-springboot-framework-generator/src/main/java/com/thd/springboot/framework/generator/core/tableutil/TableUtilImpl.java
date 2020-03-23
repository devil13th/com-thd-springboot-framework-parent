package com.thd.springboot.framework.generator.core.tableutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thd.springboot.framework.generator.CodeGenColumnConfig;
import com.thd.springboot.framework.generator.core.dbtype.DbType;
import com.thd.springboot.framework.generator.core.dto.Table;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Data
public abstract class TableUtilImpl implements TableUtil {
	@Value("${schema}")
	protected String schema;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//数据库类型 oracle mysql sqlserver
	protected DbType dbtype;


	public Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected Map<String,String> dataTypeMap = new HashMap<String,String>();
	
	protected TableUtilImpl(DbType dbType){

		this.dbtype = dbType;
	}





	public abstract Table loadTable(String dbTableName) throws Exception;





}
