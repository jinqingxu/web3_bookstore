package com.module.dao.daoimpl;

import com.module.bean.Comment;
import com.module.bean.Order;
import com.module.dao.CommentDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by jinqingxu on 17/06/2017.
 */
public class CommentDAOImpl extends HibernateDaoSupport implements CommentDAO {
    public void insert(String username,String bookname,String content) {
        Comment comment=new Comment();
        comment.setBookname(bookname);
        comment.setUsername(username);
        comment.setContent(content);
        this.getHibernateTemplate().save(comment);
    }

    public List<Comment> getAllComment() {
        String sql = "from Comment as comment";
        List<Comment> result=(List<Comment>) this.getHibernateTemplate().find(sql);
        if(result.size()>0){
            return result;
        }
        else{
            return null;
        }
    }
}
