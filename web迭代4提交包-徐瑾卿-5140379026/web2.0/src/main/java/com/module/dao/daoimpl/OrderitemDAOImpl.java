package com.module.dao.daoimpl;

/**
 * Created by piglet on 2016/6/12.
 */
import com.module.bean.Orderitem;
import com.module.bean.Book;
import com.module.bean.Order;
import com.module.dao.OrderitemDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrderitemDAOImpl  extends HibernateDaoSupport implements OrderitemDAO {
    public  void insert(String orderid,int tmpid,int cartnumber){
        Orderitem tmpitem=new Orderitem();
       Book tmpbook=new Book();
        tmpbook.setId(tmpid);
        tmpitem.setBook(tmpbook);
        tmpitem.setOrderid(orderid);
        tmpitem.setNumber(cartnumber);
        this.getHibernateTemplate().save(tmpitem);

    }
}
