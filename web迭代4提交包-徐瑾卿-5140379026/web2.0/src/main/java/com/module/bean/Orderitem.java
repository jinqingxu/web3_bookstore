package com.module.bean;


/**
 * Created by piglet on 2016/6/9.
 */
public class Orderitem {
    private int id;
    private int number;
    private Book book;
  private String orderid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setBook(Book book){
        this.book=book;
    }
    public Book getBook(){
        return book;
    }
    public void setNumber(int  number){
        this.number=number;
    }
    public int getNumber(){
        return number;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderid() {
        return orderid;
    }
}
