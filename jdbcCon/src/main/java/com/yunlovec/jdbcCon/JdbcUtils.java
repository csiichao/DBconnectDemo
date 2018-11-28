package com.yunlovec.jdbcCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

//通过上面的工具就可以获取到properties文件中的键值从而可以加载驱动 获取链接 从而 可以增删改查
	private static Connection conn = null;

	public static Connection getConn() {
		PropertiesUtil.loadFile("jdbc.properties");
		String driver = PropertiesUtil.getPropertyValue("driver");
		String url = PropertiesUtil.getPropertyValue("url");
		String username = PropertiesUtil.getPropertyValue("username");
		String password = PropertiesUtil.getPropertyValue("password");

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			close();
		}
		return conn;
	}

	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过用户名到数据库中获取凭证密码
	 * 
	 * @param userName
	 * @return
	 */
	
	@SuppressWarnings("unused")
	public static String getPasswordByUserName(String id) {
		// SQL语句  
		String sql = "select * from sys_dict where code = " + "'" + id + "'";
		Connection conn = JdbcUtils.getConn();
		Statement stmt = null;
		ResultSet ret = null;
		String countryname = null;
		try {
			stmt = conn.createStatement();
			// 执行语句，得到结果集  
			ret = stmt.executeQuery(sql);
			System.out.println(sql);
			while (ret.next()) {
				// 这里只查询的密码
				countryname = ret.getString(1)+ ret.getString(2)+ret.getString(3)+ret.getString(4);
				System.out.println(countryname);
			}
			ret.close();
			conn.close();// 关闭连接  
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return countryname;
	}
}
