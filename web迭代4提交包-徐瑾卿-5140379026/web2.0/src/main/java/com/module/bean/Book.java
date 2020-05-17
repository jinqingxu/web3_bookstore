package com.module.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by piglet on 2016/6/9.
 */
public class Book implements Serializable {
    private int stockbalance;
    private String publishhouse;
    private String photopath;
    private String description;
    private String bookname;
    private double price;
    private int id;
    private String category;

    private int version;
    private int mylike;

    public int getMylike() {
        return mylike;
    }

    public void setMylike(int mylike) {
        this.mylike = mylike;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getStockbalance() {
        return stockbalance;
    }

    public void setStockbalance(int stockbalance) {
        this.stockbalance = stockbalance;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath= photopath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPublishhouse() {
        return publishhouse;
    }

    public void setPublishhouse(String publishhouse) {
        this.publishhouse = publishhouse;
    }
}
