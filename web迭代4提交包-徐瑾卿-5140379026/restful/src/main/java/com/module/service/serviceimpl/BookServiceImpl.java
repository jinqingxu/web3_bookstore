package com.module.service.serviceimpl;

/**
 * Created by piglet on 2016/6/11.
 */

import com.module.bean.Book;
import com.module.dao.BookDAO;

import com.module.service.BookService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookServiceImpl implements BookService
{
    private BookDAO bookDao;
    private String category;
    public BookDAO getBookDao() {
        return bookDao;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    public Book querybyname(String bookname){
       return this.bookDao.querybyname(bookname);


    }


}

