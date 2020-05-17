package com.module.action;

/**
 * Created by piglet on 2016/6/6.
 */
import com.module.bean.User;
import com.module.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import  java.util.Map;
public class RegisterAction  extends ActionSupport {
    private User user;
    private UserService userService;
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String execute() throws Exception {
        String result;
        if(this.userService.register(user)==true) {
            result="success";
            return result;
        }

        else{
            ActionContext context = ActionContext.getContext();
            Map request = (Map)context.get("request");

            request.put("wmessage","用户已经存在！");
            request.put("raddress","/register.jsp");
            result="fail";
            return result;
        }




    }
}
