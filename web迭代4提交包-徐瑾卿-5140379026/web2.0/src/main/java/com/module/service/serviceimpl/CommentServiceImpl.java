package com.module.service.serviceimpl;

import com.module.bean.Comment;
import com.module.dao.CommentDAO;
import com.module.service.CommentService;

import java.util.List;

/**
 * Created by jinqingxu on 17/06/2017.
 */
public class CommentServiceImpl implements CommentService {
    private CommentDAO commentDAO;

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getAllComment() {
        return this.commentDAO.getAllComment();
    }

    public void insert(String username, String bookname, String content) {
        this.commentDAO.insert(username,bookname,content);
    }
}
