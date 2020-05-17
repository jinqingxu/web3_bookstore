package com.module.action;

import com.module.bean.User;
import com.module.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by piglet on 2016/6/8.
 */
public class UserDeleteAction {
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
        String result="success";
        this.userService.delete(id);
        return result;
    }
}
