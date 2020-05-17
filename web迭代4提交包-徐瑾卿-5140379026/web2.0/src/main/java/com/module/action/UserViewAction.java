package com.module.action;

import com.module.bean.User;
import com.module.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by piglet on 2017/3/21.
 */
public class UserViewAction {
    private UserService userService;
    private JSONObject jsonresult;
    public UserService getUserService() {
        return userService;
    }

    public void setJsonresult(JSONObject jsonresult) {
        this.jsonresult = jsonresult;
    }

    public JSONObject getJsonresult() {
        return jsonresult;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public String execute()throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String username=request.getUserPrincipal().getName();
        User user=this.userService.query(username);
        Map<String,Object> jsontemp = new HashMap<String,Object>();
        jsontemp.put("user",user);
        JsonConfig jsonConfig=new JsonConfig();
        jsonresult = JSONObject.fromObject(jsontemp,jsonConfig);
        String debuf=jsonresult.toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonresult);
        out.flush();
        out.close();
        return "success";

    }
}
