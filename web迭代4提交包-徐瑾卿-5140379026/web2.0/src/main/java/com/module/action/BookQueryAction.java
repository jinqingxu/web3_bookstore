package com.module.action;

import com.module.bean.Book;
import com.module.service.BookService;
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
 * Created by piglet on 2016/6/11.
 */
public class BookQueryAction {
    private BookService bookService;
    public BookService getBookService() {
        return bookService;
    }
    private JSONObject jsonresult;
    public void setJsonresult(JSONObject jsonresult){
        this.jsonresult = jsonresult;
    }
    public JSONObject getJsonresult(){
        return jsonresult;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    public String updatemylike() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        int  id= Integer.parseInt(request.getParameter("id"));
        this.bookService.updatelike(id);
        Book result=this.bookService.querybyid(id);
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(new String[]{"orderitems"});
        jsonresult = JSONObject.fromObject(result,jsonConfig);
        String mylike=jsonresult.getString("mylike");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonresult);
        out.flush();
        out.close();
        return "success";
    }
    public String querybyid()throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        int  id= Integer.parseInt(request.getParameter("id"));
        Book result=this.bookService.querybyid(id);
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(new String[]{"orderitems"});
        jsonresult = JSONObject.fromObject(result,jsonConfig);
        String mylike=jsonresult.getString("mylike");
        String price=jsonresult.getString("price");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonresult);
        out.flush();
        out.close();
        return "success";
    }
    public String querybyname()throws Exception{
        return "success";
    }
}
