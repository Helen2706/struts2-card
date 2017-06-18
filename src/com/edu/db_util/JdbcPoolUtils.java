package com.edu.db_util;

import java.sql.*;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcPoolUtils {
	//加载默认的配置文件c3p0.properties，并创建数据源
	private static DataSource dataSource = new ComboPooledDataSource();
	//从数据源获取session对象
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	//将连接资源释放
	public static void close(ResultSet resultSet,PreparedStatement preparedStatement,Connection connection) throws SQLException{
		try {
			if(resultSet!=null){resultSet.close();}
		} finally {
			try {
				if(preparedStatement!=null){preparedStatement.close();}
			} finally {
				if(connection!=null){connection.close();}
			}
		}
	}
	//实现记录的添加、删除和修改，返回受影响的记录数
	public static int dbCUD(String sql,Object...params){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int row = 0;
		try {
			connection = JdbcPoolUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++)
					preparedStatement.setObject(i+1, params[i]);
			}
			row = preparedStatement.executeUpdate();
			JdbcPoolUtils.close(null, preparedStatement, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	//实现查询，返回查询结果集
	public static ResultSet query(String sql,Object...params){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++)
					preparedStatement.setObject(i+1, params[i]);
			}
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
}
