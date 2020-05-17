package com.module.action;

import com.module.bean.User;
import com.module.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by piglet on 2016/6/8.
 */
public class UserQueryAction {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public String userquery()throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String password= request.getParameter("password");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        String result="sucess";
        if(this.userService.insert(user)==false){
            result="fail";
        }
        return result;
    }
}
