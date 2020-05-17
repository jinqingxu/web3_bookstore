package com.module.dao.daoimpl;
import com.module.bean.Book;
import org.hibernate.*;
/**
 * Created by piglet on 2016/6/12.
 */
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import com.module.bean.Order;
import com.module.dao.OrderDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.module.bean.User;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.List;
import java.sql.Date;

public class OrderDAOImpl extends HibernateDaoSupport implements OrderDAO {


    public List<Order> getorder(String username) {
        String sql = "from Order as order where order.username='"+username+"'";
        List<Order> result=(List<Order>) this.getHibernateTemplate().find(sql);
        if(result.size()>0){
            return result;
        }
        else{
            return null;
        }
    }

    public void insert(String username, Timestamp cal,Double sumprice,int sumnumber,String orderid){
        Order myorder=new Order();
        myorder.setUsername(username);
        myorder.setDate(cal);
        myorder.setSumprice(sumprice);
        myorder.setSumnumber(sumnumber);
        myorder.setOrderid(orderid);
        this.getHibernateTemplate().save(myorder);
    }
    public int getorderid(String username,Timestamp date){
        String sql = "from Order as order where order.username='"+username+"' and order.date='"+date+"'";
        List<Order> result=(List<Order>) this.getHibernateTemplate().find(sql);
        if(result.size()>0){
            return result.get(0).getId();
        }
        else{
            return -1;
        }
    }
}
