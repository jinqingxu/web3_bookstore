package com.module.dao;

import com.module.bean.Comment;

import java.util.List;

/**
 * Created by jinqingxu on 17/06/2017.
 */
public interface CommentDAO {
    public void insert(String username,String bookname,String content);
    public List<Comment> getAllComment();
}
