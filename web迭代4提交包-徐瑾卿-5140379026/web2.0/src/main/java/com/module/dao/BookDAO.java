package com.module.dao;

import com.module.bean.Book;

import java.util.List;

/**
 * Created by piglet on 2016/6/11.
 */
public interface BookDAO {
    public void update(Book book);
    public boolean insert(Book book);
    public List<Book> bookGet();
    public Book querybyname(String bookname);
    public Book querybyid(int id);
    public void delete(int id);
    public int updatestockbalance(int tmpid,int cartnumber);
    public void updatelike(int id);
}
