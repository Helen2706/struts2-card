<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%><!-- 引入struts2标签库 -->
<% String path = request.getContextPath()+"/";%>	<!-- request.getContextPath()定义路径，返回站点的根目录-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
	<!-- 显示输入验证出错信息 -->
	<s:fielderror cssStyle="color:red"></s:fielderror>
	<!-- 执行Action时，出错信息的显示 -->
	<font color="red"><s:property value="message"/></font><br>
	<h3 align="left">欢迎注册我们的系统，请认真填写您的信息</h3>
	<form name="register" action="<%=path %>user/register" method="post">
		<table>
			<tr>
				<td align="right">账户名：</td>
				<td>
					<input type="text" name="user.userName" value="${user.userName}">
				</td>
			</tr>
			<tr>
				<td align="right">为您的账户设置密码：</td>
				<td>
					<input type="password" name="user.userPassword">
				</td>
			</tr>
			<tr>
				<td align="right">再次确认您的密码：</td>
				<td>
					<input type="password" name="re_password">
				</td>
			</tr>
			<tr>
				<td align="right">真实姓名：</td>
				<td>
					<input type="text" name="user.userRealName" value="${user.userRealName }">
				</td>
			</tr>
			<tr>
				<td align="right"><input type="submit" value="提交"></td>
				<td colspan="1">
					<input type="reset" value="重新填写">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>