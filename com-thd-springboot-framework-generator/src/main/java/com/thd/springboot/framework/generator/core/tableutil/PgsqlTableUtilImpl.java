package com.thd.springboot.framework.generator.core.tableutil;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.thd.springboot.framework.generator.CodeGenColumnConfig;
import com.thd.springboot.framework.generator.core.dbtype.DbType;
import com.thd.springboot.framework.generator.core.dto.Column;
import com.thd.springboot.framework.generator.core.dto.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class PgsqlTableUtilImpl extends TableUtilImpl {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public PgsqlTableUtilImpl(){
		super(DbType.MYSQL);
		this.dataTypeMap.put("integer", "java.lang.Integer");
		this.dataTypeMap.put("varchar", "java.lang.String");
		this.dataTypeMap.put("text", "java.lang.String");
		this.dataTypeMap.put("date", "java.util.Date");
		this.dataTypeMap.put("decimal", "java.lang.Double");
		this.dataTypeMap.put("numeric", "java.lang.Double");
		this.dataTypeMap.put("float", "java.lang.Float");
		this.dataTypeMap.put("double", "java.lang.Double");
		this.dataTypeMap.put("datetime", "java.util.Date");
		this.dataTypeMap.put("text", "java.lang.String");
	}
	
	@Override
	public Table loadTable(String dbTableName) throws Exception {



		List<CodeGenColumnConfig> res = new ArrayList<CodeGenColumnConfig>();



		// 查询表信息
		String queryTableSql = " select " +
				" table_name as tableName," +
				" table_name as tableComment," +
				" table_schema as tableSchema" +
				" from " +
				" information_schema.tables " +
				" where table_name = ? and table_schema = ?  ";
		this.getLog().info(JSONObject.toJSONString(queryTableSql));
		this.getLog().info(JSONObject.toJSONString(String.format("params:%s,%s",dbTableName,this.getSchema())));
		//System.err.println(queryTableSql);
		//System.err.println(String.format("params: \"%s\",\"%s\"",dbTableName,this.getSchema()));
		List<Map<String,Object>> queryTableResult = this.getJdbcTemplate().queryForList(queryTableSql,dbTableName,this.getSchema());
		// 表不存在或没有字段
		if(queryTableResult == null || queryTableResult.size() != 1 ){
			throw new RuntimeException("Table Not Be Founed  [" + dbTableName + "]");
		}
		Map<String,Object> tableInfo = queryTableResult.get(0);
		// 表信息
		Table table = Table.createTable(tableInfo.get("tableName").toString(),tableInfo.get("tableSchema").toString());
		this.getLog().info(JSONObject.toJSONString(table));





		// 查询字段信息
		String sql = "select "
					+ "TABLE_NAME as tableName,"//1表英文名
					+ "COLUMN_NAME as columnName,"//2字段英文名
					+ "IS_NULLABLE as isNullAble,"//3是否可以空
					+ "DATA_TYPE as dataType,"//4数据类型
					//+ "COLUMN_KEY as columnKey,"//5是否主键
					+ "COLUMN_NAME as columnComment "//6字段备注
					+ " from information_schema.COLUMNS where TABLE_NAME = ? "
					+ " and TABLE_SCHEMA = ?  order by ORDINAL_POSITION asc";
//		System.err.println(sql);
//		System.err.println(String.format("params: \"%s\",\"%s\"",dbTableName,this.getSchema()));
		this.getLog().info(JSONObject.toJSONString(sql));
		this.getLog().info(JSONObject.toJSONString(String.format("params: %s,%s",dbTableName,this.getSchema())));

		List<Map<String,Object>> columns = this.getJdbcTemplate().queryForList(sql,dbTableName,this.getSchema());
		// 表不存在或没有字段
		if(columns == null || columns.size() ==0){
			throw new RuntimeException("Table Not Be Founed Or There Is No Column In This Table [" + dbTableName + "]");
		}



		// 查询主键
		String pkSQL = "SELECT " +
				" pg_attribute.attname AS colname " +
				" FROM " +
				" pg_constraint " +
				" INNER JOIN pg_class ON pg_constraint.conrelid = pg_class.oid " +
				" INNER JOIN pg_attribute ON pg_attribute.attrelid = pg_class.oid " +
				" AND pg_attribute.attnum = pg_constraint.conkey[1] " +
				" INNER JOIN pg_type ON pg_type.oid = pg_attribute.atttypid " +
				" WHERE " +
				" pg_class.relname = ? " +
				" AND pg_constraint.contype = 'p'";

		List<Map<String,Object>> pkList = this.getJdbcTemplate().queryForList(pkSQL,dbTableName);

		List<String> pkColumnNameList = new ArrayList<String>();
		if(null != pkList && pkList.size() > 0){
			pkList.forEach( item -> {
				pkColumnNameList.add(item.get("colname").toString());
			});
		}


		List<Column> allColumns = columns.stream().map(columnMap -> {
			String columnName = columnMap.get("columnName").toString();
			String dataType  = columnMap.get("dataType").toString();
			String comment = columnMap.get("columnComment").toString();
			String isNullAble = columnMap.get("isNullAble").toString();
			//this.getLog().info(JSONObject.toJSONString(columnMap));


			Column column = Column.createColumn(columnName);
			column.setComment(comment);
			column.setDbDataType(dataType);
			String javaDataType = this.dataTypeMap.get(dataType);
			if(javaDataType == null){
				throw new RuntimeException(String.format(" 数据库字段与Java字段未设置:[%s].[%s]字段类型[%s]",dbTableName,columnName,dataType));
			}
			column.setDataType(this.dataTypeMap.get(dataType));
			// 是否主键
			column.setIsPk(pkColumnNameList.contains(columnName));
			// 是否可以为空
			column.setIsNullAble("YES".equalsIgnoreCase(isNullAble));
			this.getLog().debug(JSONObject.toJSONString(column));
			return column;
		}).collect(Collectors.toList());


		List<Column> pkColumns = allColumns.stream().filter(item -> item.getIsPk()).collect(Collectors.toList());
		if(pkColumns == null || pkColumns.size() == 0 ){
			throw new RuntimeException("Pk Not Be Found");
		}

		if(pkColumns.size()>1){
			throw new RuntimeException("Number Of Pk Column More Then One");
		}

		// 主键字段
		table.setPkColumn(pkColumns.get(0));
		// 所有字段
		table.setAllColumns(allColumns);
		List<Column> normalColumns = allColumns.stream().filter(item-> !item.getIsPk()).collect(Collectors.toList());
		// 非主键字段
		table.setNormalColumns(normalColumns);




		this.getLog().debug(JSONObject.toJSONString(table, SerializerFeature.DisableCircularReferenceDetect));

		//this.getLog().info(JSONObject.toJSONString(table));
		//System.out.println(JSONObject.toJSONString(columns));
		return table;

	}

}
