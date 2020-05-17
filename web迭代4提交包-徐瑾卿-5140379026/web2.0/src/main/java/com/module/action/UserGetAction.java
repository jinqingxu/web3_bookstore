package com.module.action;

import com.module.bean.User;
import com.module.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 * Created by piglet on 2016/6/9.
 */
public class UserGetAction {
    private User user;
    private UserService userService;
    private JSONObject jsonresult;

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
    public String execute() throws Exception {
        return null;
    }
    public void setJsonresult(JSONObject jsonresult){
        this.jsonresult = jsonresult;
    }
    public JSONObject getJsonresult(){
        return jsonresult;
    }

    public  String  userget() throws Exception{
        List<User> result=this.userService.userGet();
        Map<String,Object> jsontemp = new HashMap<String,Object>();
        jsontemp.put("total",result.size());
        jsontemp.put("rows", result);
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(new String[]{"orders"});
        jsonresult = JSONObject.fromObject(jsontemp,jsonConfig);
        String debuf=jsonresult.toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonresult);
        out.flush();
        out.close();
        return  "success";



    }
}
