package com.edu.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.edu.model.user.User;

public class UserDao implements IBaseDao<User>{
	//注册时，向数据库添加一个用户
	@Override
	public int insert(User o) {
		String sql = "insert into user(userName,userPassword,userRealName) values (?,?,?)";
		Object params[]={o.getUserName(),o.getUserPassword(),o.getUserRealName()};
		return JdbcPoolUtils.dbCUD(sql, params);
	}

	//登录时在数据库中查询是否存在该用户
	@Override
	public User find(User o) {
		ResultSet resultSet = null;
		String sql="select * from user where userName=? and userPassword=?";
		Object params[]={o.getUserName(),o.getUserPassword()};
		resultSet = JdbcPoolUtils.query(sql, params);
		User user = null;
		try {
			if(resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("userName"));
				user.setUserPassword(resultSet.getString("userPassword"));
				user.setUserRealName(resultSet.getString("userRealName"));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public int insertList(List<User> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteList(int... ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(User o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
