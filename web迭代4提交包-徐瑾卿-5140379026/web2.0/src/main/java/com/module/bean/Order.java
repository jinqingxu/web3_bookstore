package com.module.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
/**
 * Created by piglet on 2016/6/9.
 */
public class Order {
    private int id;
    private String username;
    private Timestamp date;
    private String orderid;
    private Double sumprice;
    private int sumnumber;


    public int getSumnumber() {
        return sumnumber;
    }

    public void setSumnumber(int sumnumber) {
        this.sumnumber = sumnumber;
    }

    public Double getSumprice() {
        return sumprice;
    }
    public void setSumprice(Double sumprice) {
        this.sumprice = sumprice;
    }


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDate(){
        return date;
    }
    public void setDate(Timestamp date){
        this.date=date;
    }
}
