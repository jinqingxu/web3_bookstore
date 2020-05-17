package com.module.service;

/**
 * Created by piglet on 2016/6/11.
 */
import com.module.bean.Book;
import com.module.entity.Cart;

import java.util.List;
public interface BookService
{


    public void update(Book book);
    public boolean insert(Book book);
    public List<Book> bookGet();
    public Book querybyname(String bookname);
    public Book querybyid(int id);
    public void delete(int id);
    public int updatestockbalance(int tmpid,int cartnumber);
    public String getCategory();
    public void setCategory(String category);
    public void updatelike(int id);



}
