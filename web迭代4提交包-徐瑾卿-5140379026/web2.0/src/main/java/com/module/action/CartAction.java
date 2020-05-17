package com.module.action;

import com.module.bean.Book;
import com.module.entity.Cart;
import com.module.service.CartService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by piglet on 2016/6/10.
 */
public class CartAction {
    private JSONObject jsonresult;
    private CartService cartService;
    public CartService getCartService() {
        return cartService;
    }
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public String cartview(){
        List<Cart> mycart=this.cartService.getMycart();
        ActionContext context = ActionContext.getContext();
        Map request = (Map)context.get("request");
        request.put("mycart",mycart);
        return  "success";
    }
    public String cartinsert() throws Exception {
        String result;
        HttpServletRequest request = ServletActionContext.getRequest();
        int tmpbookid=Integer.parseInt(request.getParameter("bookid"));
        int tmpbooknumber=Integer.parseInt(request.getParameter("booknumber"));
        if(this.cartService.validate(tmpbookid,tmpbooknumber)==true) {
            /*ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            List<Cart>mycart=null;
            mycart=(List<Cart>)session.get("carts");
            if(mycart==null) {
                mycart=new ArrayList<Cart>();
            }
            List<Cart> newcarts=this.cartService.insertcart(mycart,tmpbookid,tmpbooknumber);
            session.put("carts",newcarts);*/
            this.cartService.insertcart(tmpbookid,tmpbooknumber);
            Map returnvalue=new HashMap();
            String value="successfully add";
            returnvalue.put("message",value);
            jsonresult = JSONObject.fromObject(returnvalue);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/json;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.print(jsonresult);
            out.flush();
            out.close();
            result="success";
            return result;
        }

        else{
            ActionContext context = ActionContext.getContext();
            Map therequest = (Map)context.get("request");
            therequest.put("wmessage","number is larger than stockbalance!");
            therequest.put("raddress","/broswerbook.jsp");
            result="fail";
            return result;
        }




    }



}
