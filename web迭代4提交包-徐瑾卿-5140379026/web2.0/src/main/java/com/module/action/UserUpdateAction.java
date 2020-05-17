package com.module.action;

import com.module.bean.User;
import com.module.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by piglet on 2016/6/8.
 */
public class UserUpdateAction {

    private UserService userService;
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        int id=Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password= request.getParameter("password");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        String result="sucess";
        User user=new User();
        user.setId(id);
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        user.setEmail(email);
        this.userService.update(user);
        return result;
    }
}
