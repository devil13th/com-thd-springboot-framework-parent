package com.thd.springboot.framework.generator.core.tableutil;

import com.thd.springboot.framework.generator.CodeGenColumnConfig;
import com.thd.springboot.framework.generator.core.dbtype.DbType;
import com.thd.springboot.framework.generator.core.dto.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OracleTableUtilImpl extends TableUtilImpl {

	public OracleTableUtilImpl(){
        super(DbType.ORACLE);

		this.dataTypeMap.put("clob", "java.lang.String");
		this.dataTypeMap.put("date", "java.util.Date");
		this.dataTypeMap.put("number", "java.lang.Double");
		this.dataTypeMap.put("varchar2", "java.lang.String");
	}

	@Override
	public Table loadTable(String dbTableName) throws Exception {
		List<CodeGenColumnConfig> res = new ArrayList<CodeGenColumnConfig>();
        return null;
//		try{
//			conn.setAutoCommit(false);
//			String sql = "select "
//					+ "c.TABLE_NAME,"//1表英文名
//					+ "c.COLUMN_NAME,"//2字段英文名
//					+ "c.NULLABLE,"//3是否可以空
//					+ "c.DATA_TYPE,"//4数据类型
//					+ "p.constraint_name,"//5 是否主键
//					+ " cc.comments "//6字段注释
//					+ " from user_tab_columns c left join ("
//					+ "select a.table_name as table_name ,a.constraint_name as constraint_name,  a.column_name as column_name "
//					+ "from user_cons_columns a, user_constraints b  "
//					+ "where a.constraint_name = b.constraint_name  "
//					+ "and b.constraint_type = 'P'  "
//					+ "and a.table_name = upper(?) "
//					+ ")  p on c.table_name=p.table_name and c.column_name = p.column_name"
//					+ " left join user_col_comments cc on c.table_name = cc.table_name and c.column_name = cc.column_name  where c.TABLE_NAME like upper(?) order by c.COLUMN_ID asc ";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dbTableName );
//			pstmt.setString(2, dbTableName );
//			ResultSet rs = pstmt.executeQuery();
//			while(rs.next()){
//				String columnCode = rs.getString(2) == null ? "" : rs.getString(2);
//				String columnName = rs.getString(6) == null ? "" : rs.getString(6);
//				String isNullAble = "Y".equals(rs.getString(3) == null ? "" : rs.getString(3)) ? "Y":"N";
//				String columnType = rs.getString(4) == null ? "" : rs.getString(4);
//				String columnIspk = rs.getString(5) == null ? "" : rs.getString(5);
//				//System.out.println(columnCode + "|" + columnName + "|" + isNullAble + "|" + columnType + "|" + columnIspk );
//
//
//				CodeGenColumnConfig columnCfg = new CodeGenColumnConfig();
//
//
//				columnCfg.setColumnCode(columnCode.trim());
//				columnCfg.setColumnName(columnName.trim());
//				String columnJavaType = dataTypeMap.get(columnType.trim().toLowerCase());
//				if(columnJavaType == null || columnJavaType.trim().equals("")){
//					throw new RuntimeException("未找到[" +  columnCode + "]字段的对应类型[" + columnType + "]");
//				}
//				columnCfg.setColumnType(columnJavaType);
//				if(columnIspk!=null && !"".equals(columnIspk.trim())){
//					columnCfg.setIsPk(true);
//				}
//				columnCfg.setColumnDesc(columnName);
//				res.add(columnCfg);
//
//			}
//			conn.commit();
//			this.closeConn();
//		}catch(Exception e){
//			conn.rollback();
//			e.printStackTrace();
//			throw e;
//		}
//
//		return res;
	}

}
