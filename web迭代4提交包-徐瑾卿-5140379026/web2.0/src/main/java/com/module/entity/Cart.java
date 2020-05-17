package com.module.entity;

/**
 * Created by piglet on 2016/6/10.
 */
public class Cart {

    private String bookname;
    private  double price;
    private String category;
    private Integer number;
    private int bookid;
    private String picture;
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }
    public int getBookid(){
        return bookid;
    }
    public void  setBookid(int bookid){
        this.bookid=bookid;
    }
    public String getBookname() {
        return bookname;
    }
    public double getPrice() {
        return price;
    }
    public Integer getNumber(){
        return number;
    }
    public void setNumber(Integer n){
        this.number=n;
    }
    public void setBookname(String bn) {
        this.bookname=bn;
    }
    public String getCategory() {
        return category;
    }
    public void setPrice(double pr) {
        this.price = pr;
    }
    public void setCategory(String cg) {
        this.category = cg;
    }


}
