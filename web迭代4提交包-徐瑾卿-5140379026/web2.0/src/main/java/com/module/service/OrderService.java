package com.module.service;

/**
 * Created by piglet on 2016/6/12.
 */
import com.module.bean.Order;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    public void insertorder(String username,Timestamp cal,String[] checked,String[] numbers,Integer[] bookid,Double[] price) throws Exception;
    public List<Order> getorder(String username);
    public boolean exist(String username,Timestamp cal);

}
