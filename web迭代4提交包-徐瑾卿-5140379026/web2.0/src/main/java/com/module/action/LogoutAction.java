package com.module.action;
import com.module.service.CartService;
import com.module.service.CountService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.List;

/**
 * Created by piglet on 2016/6/10.
 */
public class LogoutAction  {
    private CountService countService;
    private CartService cartService;

    public CartService getCartService() {
        return cartService;
    }
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
    public CountService getCountService() {
        return countService;
    }
    public void setCountService(CountService countService) {
        this.countService = countService;
    }
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        request.getSession().invalidate();
        countService.setCount(countService.getCount()-1);
        cartService.setMycart(null);
        return "success";
    }
}
