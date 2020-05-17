package com.module.action;

import com.module.service.BookService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by piglet on 2016/6/11.
 */
public class BookDeleteAction {
    private BookService bookService;
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        int id=Integer.parseInt(request.getParameter("id"));
        String result="success";
        this.bookService.delete(id);
        return result;
    }
}
