package com.module.action;

import com.module.bean.*;
import com.module.service.CountService;
import com.module.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import  java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.module.dao.OrderDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.module.bean.User;
import org.hibernate.SessionFactory;
import java.util.List;
import com.module.hibernate.util.HibernateUtil;
public class LoginAction extends ActionSupport {

    private User user;
    private UserService userService;
    private CountService countService;
    public CountService getCountService(){
        return countService;
    }
    public void setCountService(CountService countService){
        this.countService=countService;
    }
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
        String result;
        ActionContext context = ActionContext.getContext();
        Map request = (Map)context.get("request");
        Map session = context.getSession();
        if(this.userService.login(user)==1) {

           Map application = context.getApplication();
            request.put("greeting", "欢迎您来到书店");//在请求中放置欢迎信息。

            session.put("user", user);//在session中保存user对象
            result="admin";
            return result;
        }
       else if(this.userService.login(user)==2) {
            int count=this.countService.getCount();

            this.countService.setCount(count+1);
            Map application = context.getApplication();
            request.put("greeting", "欢迎您");//在请求中放置欢迎信息。
            session.put("user", user);//在session中保存user对象
            result="user";
            return result;
        }
        else{
            session.put("wmessage","用户名或者密码错误！");
            session.put("raddress","/index.jsp");
            result="fail";
            return result;
        }




    }
}
