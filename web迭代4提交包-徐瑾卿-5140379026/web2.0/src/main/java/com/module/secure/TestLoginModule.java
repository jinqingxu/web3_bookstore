package com.module.secure;

import com.module.bean.User;
import com.module.dao.UserDAO;
import com.module.dao.daoimpl.UserDAOImpl;

import com.module.service.UserService;
import com.module.service.serviceimpl.UserServiceImpl;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TestLoginModule implements LoginModule  {
	//private UserService userService;
	private CallbackHandler handler;
	private Subject subject;
	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;
	private String login;
	private List<String> userGroups;

	private UserDAO userDao;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}





	public void initialize(Subject subject, CallbackHandler callbackHandler,
						   Map<String, ?> sharedState, Map<String, ?> options) {

		handler = callbackHandler;
		this.subject = subject;

	}

public int checklogin(String name,String password){
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/mydb";
	//  数据库的凭据
	final String USER = "root";
	final String PASS = "xu9659";
	Connection conn=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");

		// 打开一个连接
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		// 执行 SQL 查询
		Statement stmt = conn.createStatement();
		String sql;
		sql = "select * from user where username='" + name + "' ";
		ResultSet rs = stmt.executeQuery(sql);
		boolean wrong = true;
		while (rs.next()) {
			if (rs != null) {
				String realpwd = rs.getString("password");
				if (realpwd.equals(password)) {
					String roleid=rs.getString("roleid");
					return Integer.parseInt(roleid);
				}
			}
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return -1;
}
	public boolean login() throws LoginException {

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		try {

				handler.handle(callbacks);
				String name = ((NameCallback) callbacks[0]).getName();
				String password = String.valueOf(((PasswordCallback) callbacks[1])
						.getPassword());
				//setUsername(name);
				// Here we validate the credentials against some
				// authentication/authorization provider.
				// It can be a Database, an external LDAP, a Web Service, etc.
				// For this tutorial we are just checking if user is "user123" and
				// password is "pass123"
				int id = checklogin(name, password);
				if (id == 1) {
					login = name;
					userGroups = new ArrayList<String>();
					userGroups.add("admin");
					return true;
				}
				else if(id!=-1) {
					login = name;
					userGroups = new ArrayList<String>();
					userGroups.add("user");
					return true;
				}

			// If credentials are NOT OK we throw a LoginException

			throw new LoginException("Authentication failed");

		} catch (IOException e) {
			throw new LoginException(e.getMessage());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException(e.getMessage());
		}

	}


	public boolean commit() throws LoginException {

		userPrincipal = new UserPrincipal(login);
		subject.getPrincipals().add(userPrincipal);

		if (userGroups != null && userGroups.size() > 0) {
			for (String groupName : userGroups) {
				rolePrincipal = new RolePrincipal(groupName);
				subject.getPrincipals().add(rolePrincipal);
			}
		}

		return true;
	}


	public boolean abort() throws LoginException {
		return false;
	}


	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		subject.getPrincipals().remove(rolePrincipal);
		return true;
	}

}
