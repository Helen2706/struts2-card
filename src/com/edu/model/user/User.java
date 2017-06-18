package com.edu.model.user;

public class User {
	//属性名与数据库中的相同，为什么
	private Integer id;
	private String userName;
	private String userPassword;
	private String userRealName;
	public User() {
		super();
	}
	public User(String userName, String userPassword, String userRealName) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRealName = userRealName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	

}
