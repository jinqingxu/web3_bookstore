package com.module.dao;

import com.module.bean.Book;

import java.util.List;

/**
 * Created by piglet on 2016/6/11.
 */
public interface BookDAO {
    public Book querybyname(String bookname);

}
