package com.edu.action.user;

import com.edu.dao.user.UserDao;
import com.edu.model.user.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private User user;
	private String re_password;
	private String message;
	private UserDao userDao = new UserDao();
	
	public String userLogin() throws Exception{
		String forward=null;
		User user2 = userDao.find(user);
		if(user2!=null){
			forward = "success";
		}
		else{			
			message = "用户名不存在或密码错误，登录失败，请重新登录或注册";
			forward = "failure";
		}
		return forward;
	}
	
	public String userRegister() throws Exception{
		String forward = null;
		int flag = 0;		//收到影响的数据条数
		User user2 = userDao.find(user);
		if(user2!=null&&(user2.getUserName().trim().equals(user.getUserName().trim()))){
			message = "该用户名已存在，请重新注册";
			forward = "error";
		}else{
			flag = userDao.insert(user);
			if(flag==1){
				forward = "success";
			}else{
				message = "数据库写错误";
				forward = "error";
			}
			}
		return forward;
		
		
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRe_password() {
		return re_password;
	}

	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
