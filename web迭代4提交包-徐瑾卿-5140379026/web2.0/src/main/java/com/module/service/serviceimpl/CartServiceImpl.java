package com.module.service.serviceimpl;

import com.module.bean.Book;
import com.module.entity.Cart;
import com.module.service.CartService;
import com.module.dao.BookDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piglet on 2016/6/13.
 */
public class CartServiceImpl implements  CartService {
    private BookDAO bookDao;
    private List<Cart> mycart;
    public List<Cart> getMycart(){
        return mycart;
    }
    public void setMycart(List<Cart> mycart) {
        this.mycart = mycart;
    }

    public BookDAO getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    public boolean validate(int bookid, int booknumber){
        Book result=this.bookDao.querybyid(bookid);
        if(booknumber>result.getStockbalance()){
            return false;
        }
        else
            return true;
    }
    public void insertcart( int bookid, int booknumber){
        Book newbook=this.bookDao.querybyid(bookid);
        Cart newcart=new Cart();
        newcart.setBookname(newbook.getBookname());
        newcart.setPrice(newbook.getPrice());
        newcart.setNumber(booknumber);
        newcart.setCategory(newbook.getCategory());
        newcart.setBookid(newbook.getId());
        newcart.setPicture(newbook.getPhotopath());
        if(mycart==null){
            mycart=new ArrayList<Cart>();
        }
        System.out.println("numofcart");
        System.out.println(mycart.size());
        mycart.add(newcart);

    }

}
