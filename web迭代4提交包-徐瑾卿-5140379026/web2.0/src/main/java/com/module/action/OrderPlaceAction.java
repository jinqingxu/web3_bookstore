package com.module.action;
import com.module.service.*;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

import com.module.entity.Cart;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
//import test.Producer;
import com.module.message.ReplyMessage;

/**
 * Created by piglet on 2016/6/12.
 */
public class OrderPlaceAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPlaceAction.class);
    private JmsTemplate jmsTemplate;
    private Destination requestDestination;
    private Destination replyDestination;
    private static ConcurrentMap<String, ReplyMessage> concurrentMap = new ConcurrentHashMap<String, ReplyMessage>();
    private OrderService orderService;
    private CartService cartService;
    private JSONObject jsonresult;

    public CartService getCartService() {
        return cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setJsonresult(JSONObject jsonresult){
        this.jsonresult = jsonresult;
    }
    public JSONObject getJsonresult(){
        return jsonresult;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    public Destination getRequestDestination() {
        return requestDestination;
    }
    public void setRequestDestination(Destination requestDestination) {
        this.requestDestination = requestDestination;
    }
    public Destination getReplyDestination() {
        return replyDestination;
    }
    public void setReplyDestination(Destination replyDestination) {
        this.replyDestination = replyDestination;
    }
    public String sendMessage(String me) {
        final String message=me;
        ReplyMessage replyMessage = new ReplyMessage();
        final String correlationID = UUID.randomUUID().toString();
        concurrentMap.put(correlationID, replyMessage);

        jmsTemplate.send(requestDestination, new MessageCreator() {
            public Message createMessage(javax.jms.Session session) throws JMSException {
                Message msg = session.createTextMessage(message);
                msg.setJMSCorrelationID(correlationID);
                msg.setJMSReplyTo(replyDestination);
                return msg;
            }
        });

        return "success";
    }
    public String orderplace() throws Exception{
        DecimalFormat df0=new DecimalFormat("###.00");
        HttpServletRequest request = ServletActionContext.getRequest();
        String tmpchecked= request.getParameter("checked");
        String tmpnumbers= request.getParameter("numbers");
        //String[] checked=tmpchecked.split("\\|");
        //String[] numbers=tmpnumbers.split("\\|");
        ActionContext context = ActionContext.getContext();
        String username=request.getUserPrincipal().getName();
        java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());
        //int[] re=null;
       //re=this.orderService.insert(username,currentDate,checked,numbers);
        String message="";
        message+=username;
        message+='@';
        message+=currentDate.toString();
        message+='@';
        message+=tmpchecked;
        message+='@';
        message+=tmpnumbers;
        message+='@';
        List<Cart> mycart=this.cartService.getMycart();
        String strbookid="";
        String strprice="";
        for(int i=0;i<mycart.size();++i){
            strbookid+=mycart.get(i).getBookid();
            strbookid+="|";
            strprice+=mycart.get(i).getPrice();
            strprice+="|";
        }
        message+=strbookid;
        message+='@';
        message+=strprice;
        sendMessage(message);
        Map result=new HashMap();
        result.put("message","提交成功！");
        jsonresult = JSONObject.fromObject(result);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonresult);
        out.flush();
        out.close();
        return "success";
    }
}
