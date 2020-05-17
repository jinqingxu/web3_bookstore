package com.module.service.serviceimpl;

import com.module.bean.Order;
import com.module.dao.OrderDAO;
import com.module.redis.RedisUtil;
import com.module.service.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/**
 * Created by piglet on 2016/6/12.
 */
public class OrderServiceImpl implements OrderService {
    private BookService bookService;
    private CartService cartService;
    private UserService userService;
    private OrderitemService orderitemService;
    private OrderDAO orderDao;
    public void setOrderitemService(OrderitemService orderitemService) {
        this.orderitemService = orderitemService;
    }
    public OrderitemService getOrderitemService() {
        return orderitemService;
    }
    public BookService getBookService() {
        return bookService;
    }
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    public CartService getCartService() {
        return cartService;
    }
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public OrderDAO getOrderDao() {
        return orderDao;
    }
    public void setOrderDao(OrderDAO orderDao) {
        this.orderDao = orderDao;
    }
    public List<Order> getorder(String username){
        return this.orderDao.getorder(username);
    }
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={Exception.class,RuntimeException.class})
    public void insertorder(String username,Timestamp cal,String[] checked,String[] numbers,Integer[] bookid,Double[] price) throws Exception {
        Double sumprice = 0.0;
        int sumnumber = 0;
        for(int i=0;i<checked.length;++i){
            int index = Integer.parseInt(checked[i]);
            Integer tmpid = bookid[index];
            Integer cartnumber = Integer.parseInt(numbers[i]);
            sumprice += Double.valueOf(price[i]) * cartnumber;
            sumnumber += cartnumber;
        }
        String orderid=username+cal.toString();
        this.userService.updatescore(username);
        this.orderDao.insert(username,cal,sumprice,sumnumber,orderid);

        for (int i = 0; i < checked.length; i++) {
            int index = Integer.parseInt(checked[i]);
            Integer tmpid = bookid[index];
            Integer cartnumber = Integer.parseInt(numbers[i]);
            int re = this.bookService.updatestockbalance(tmpid, cartnumber);
            if (re == -1) {
                RedisUtil redisUtil=new RedisUtil();
                redisUtil.addFail(username,cal,sumnumber,sumprice);
                throw new Exception("库存不足");
            }
            this.bookService.updatelike(tmpid);
            this.orderitemService.insert(orderid,tmpid,cartnumber);
        }



    }

    public boolean exist(String username, Timestamp cal) {
        if(this.orderDao.getorderid(username,cal)==-1)return false;
        else
            return true;
    }
}
