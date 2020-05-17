package com.module.service.serviceimpl;

/**
 * Created by piglet on 2016/6/11.
 */
import java.util.List;
import com.module.dao.BookDAO;
import com.module.bean.Book;
import com.module.entity.Cart;
import com.module.service.BookService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BookServiceImpl implements BookService
{
    private BookDAO bookDao;
    private String category;
    public BookDAO getBookDao() {
        return bookDao;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public void update(Book book){
        this.bookDao.update(book);
    }

    public void delete(int id){
        this.bookDao.delete(id);
    }

    public boolean insert(Book book){
        return this.bookDao.insert(book);
    }

    @Transactional(propagation= Propagation.SUPPORTS,isolation =Isolation.READ_COMMITTED)
    public void updatelike(int id){ this.bookDao.updatelike(id);}

    @Transactional(isolation =Isolation.READ_COMMITTED)
    public List<Book> bookGet(){
        return this.bookDao.bookGet();
    }
    public Book querybyname(String bookname){
        return this.bookDao.querybyname(bookname);
    }
    public Book querybyid(int id){
        return this.bookDao.querybyid(id);
    }

    @Transactional(propagation= Propagation.MANDATORY,isolation = Isolation.READ_COMMITTED)
    public int updatestockbalance(int tmpid,int cartnumber){
        return this.bookDao.updatestockbalance(tmpid,cartnumber);
    }

}

