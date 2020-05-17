package com.module.action;

import com.module.bean.Book;
import com.module.service.BookService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piglet on 2016/6/11.
 */
public class BookGetAction {
    private Book book;
    private BookService bookService;
    private JSONObject jsonresult;

    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
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

    public  String bookget() throws Exception{
        List<Book> result=this.bookService.bookGet();
        Map<String,Object> jsontemp = new HashMap<String,Object>();
        jsontemp.put("total",result.size());
        jsontemp.put("rows", result);
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(new String[]{"orderitems"});
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
