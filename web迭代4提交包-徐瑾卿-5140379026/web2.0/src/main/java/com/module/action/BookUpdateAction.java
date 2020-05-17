package com.module.action;

import com.module.bean.Book;
import com.module.service.BookService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by piglet on 2016/6/11.
 */
public class BookUpdateAction {
    private BookService bookService;
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        int id=Integer.parseInt(request.getParameter("id"));
        String bookname = request.getParameter("bookname");
        double  price= Double.parseDouble(request.getParameter("price"));
        String category=request.getParameter("category");
        int  stockbalance=Integer.parseInt(request.getParameter("stockbalance"));
        String publishhouse= request.getParameter("publishhouse");
        String photopath=request.getParameter("photopath");
        String description=request.getParameter("description");
        Book book=new Book();
        book.setStockbalance(stockbalance);
        book.setPublishhouse(publishhouse);
        book.setPrice(price);
        book.setBookname(bookname);
        book.setCategory(category);
        book.setDescription(description);
        book.setPhotopath(photopath);
        book.setId(id);

        this.bookService.update(book);

    }
}
