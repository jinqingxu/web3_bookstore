package com.module.action;

import com.module.bean.Book;
import com.module.service.BookService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinqingxu on 2017/5/19.
 */
@Path("/book")
public class BookQueryAction extends ActionSupport {
   private BookService bookService;

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public  String query(@QueryParam("bookname") String bookname,@QueryParam("callback") String jsonpcallback){
        /*System.out.println("bookname"+bookname);
        System.out.println("jsonpcallback"+jsonpcallback);
        Book book=this.bookService.querybyname(bookname);
        Map<String,Object> jsontemp = new HashMap<String,Object>();
        jsontemp.put("bookname",book.getBookname());
        jsontemp.put("stockbalance", book.getStockbalance());
        jsontemp.put("version",book.getVersion());
        jsontemp.put("price", book.getPrice());
        jsontemp.put("publishhouse",book.getPublishhouse());
        jsontemp.put("photopath", book.getPhotopath());
        jsontemp.put("mylike",book.getMylike());
        jsontemp.put("id", book.getId());
        jsontemp.put("description", book.getDescription());
        jsontemp.put("category",book.getCategory());
         JSONObject jsonresult = JSONObject.fromObject(jsontemp);
        String result=jsonresult.toString();
        System.out.println(jsonpcallback+'('+result+')');
        return jsonpcallback+'('+result+')';*/

        Book book=this.bookService.querybyname(bookname);
        JSONObject json = JSONObject.fromObject(book);
        String result=json.toString();
        return jsonpcallback+'('+result+')';
}
}
