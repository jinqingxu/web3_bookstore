package com.module.action;

import com.module.bean.Book;
import com.module.bean.User;
import com.module.permission.CategoryPemission;
import com.module.service.BookService;
import com.module.service.CountService;
import com.module.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.module.bean.User;
import com.module.permission.CategoryPemission;
import com.module.secure.TestLoginModule;
import com.module.service.BookService;
import com.module.service.CountService;
import com.module.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import javax.security.auth.spi.LoginModule;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piglet on 2016/6/11.
 */
public class NewBookListAction  {
    private CountService countService;
    private BookService bookService;
    private List<Book> result;
    private JSONObject jsonresult;
    private UserService userService;
    private String testSelect;

    public String getTestSelect() {
        return testSelect;
    }

    public void setTestSelect(String testSelect) {
        this.testSelect = testSelect;
    }
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public CountService getCountService(){
        return countService;
    }
    public void setCountService(CountService countService){
        this.countService=countService;
    }
    public List<Book> getResult() {
        return result;
    }
    public void setResult(List<Book> result) {
        this.result= result;
    }
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    public void setJsonresult(JSONObject jsonresult){
        this.jsonresult = jsonresult;
    }
    public JSONObject getJsonresult(){
        return jsonresult;
    }

    public  String  execute() throws Exception{
        HttpServletRequest requests = ServletActionContext.getRequest();
        ActionContext context = ActionContext.getContext();
        Map request = (Map)context.get("request");
        Map session=(Map)context.get("session");
        //String username=userService.getUsername();
        List<Book> result=this.bookService.bookGet();
        String username=requests.getUserPrincipal().getName();
        User user=this.userService.query(username);
        testSelect=(String)session.get("category");
        List<Book> newresult=new ArrayList<Book>();
        if(!(testSelect.equals("book"))){
            Integer i=0;
            for(i=0;i<result.size();++i){
                String[] clist=result.get(i).getCategory().split("\\|");
                for(int j=0;j<clist.length;++j){
                    if(clist[j].equals(testSelect)){
                        newresult.add(result.get(i));
                    }
                }
            }
            request.put("books",newresult);
        }
        else{
            request.put("books",result);
        }
        //int newcount=this.countService.getCount();
        //this.countService.setCount(newcount+1);
        request.put("count",this.countService.getCount());
        return  "success";



    }
}

