package com.module.action;

import com.module.bean.Order;
import com.module.redis.RedisUtil;
import com.module.service.OrderService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by piglet on 2017/3/21.
 */
public class OrderViewAction {
private OrderService orderService;
    public OrderService getOrderService() {
        return orderService;
    }
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    public String execute()throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String username=request.getUserPrincipal().getName();
        List<Order> myorder=this.orderService.getorder(username);
        ActionContext context = ActionContext.getContext();
        Map therequest = (Map)context.get("request");
        therequest.put("myorder",myorder);
        RedisUtil redisUtil=new RedisUtil();
        List<Order> fails=redisUtil.readfail(username);
        therequest.put("fails",fails);
        return "success";


    }
}
