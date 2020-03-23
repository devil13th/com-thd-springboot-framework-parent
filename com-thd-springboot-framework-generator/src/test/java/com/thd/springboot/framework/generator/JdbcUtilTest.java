package com.thd.springboot.framework.generator;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;


public class JdbcUtilTest extends TestCase {
	@Test
	public void testloadOracleColumnCfg() throws Exception{
		//数据库链接地址
		String url = "jdbc:oracle:thin:@172.26.200.100:1521:oradb";
		//数据库用户名
		String uname = "dsmis";
		//数据库密码
		String upwd = "dsmis123456";
		//数据库类型
		String dbtype = "oracle";
//		TableUtil ju = new OracleTableUtilImpl(uname,upwd,url,"");
//		List l = ju.loadColumnCfg("work_survey_list");
//		System.out.println(l);
	}


	@Test
	public void testloadMySQLColumnCfg() throws Exception{



//		//数据库链接地址
//		String url = "jdbc:mysql://127.0.0.1:3306/devil13th_pm?serverTimezone=UTC&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&zeroDateTimeBehavior=convertToNull";
//		//数据库用户名
//		String uname = "root";
//		//数据库密码
//		String upwd = "123456";
//		//数据库类型
//		String dbtype = "mysql";
//		TableUtil ju = new MysqlTableUtilImpl(uname,upwd,url,"");
//		List<CodeGenColumnConfig> l = ju.loadColumnCfg("sys_user");
//		l.stream().forEach(item -> {
//			System.out.println(item);
//		});
	}
}
