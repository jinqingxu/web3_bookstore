package com.module.dao;

/**
 * Created by piglet on 2016/6/12.
 */
import com.module.bean.Order;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OrderDAO {
    void insert(String username,Timestamp cal,Double sumprice,int sumnumber,String orderid);
    int getorderid(String username,Timestamp date);
    List<Order> getorder(String username);


}
