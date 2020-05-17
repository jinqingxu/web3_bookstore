package com.module.service;

import com.module.entity.Cart;

import java.util.List;

/**
 * Created by piglet on 2016/6/13.
 */
public interface CartService {
    public void insertcart( int bookid, int booknumber);
    public boolean validate(int bookid, int booknumber);
    public List<Cart> getMycart();
    public void setMycart(List<Cart> mycart);
}
