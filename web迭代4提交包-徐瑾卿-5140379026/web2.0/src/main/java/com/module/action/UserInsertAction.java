package com.module.action;

/**
 * Created by piglet on 2016/6/8.
 */
import com.module.bean.User;
import com.module.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.apache.struts2.ServletActionContext;

public class UserInsertAction {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public String execute()throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String password= request.getParameter("password");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        User user=new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);

        String result="success";
        if(this.userService.insert(user)==false){
            result="fail";
            ActionContext context = ActionContext.getContext();

            Map therequest = (Map)context.get("request");
            therequest.put("wmessage","已经存在！");
            therequest.put("raddress","/adminuser.jsp");
        }
        return result;
    }


}
