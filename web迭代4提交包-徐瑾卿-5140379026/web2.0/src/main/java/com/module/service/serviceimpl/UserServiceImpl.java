package com.module.service.serviceimpl;

import java.util.List;
import com.module.dao.UserDAO;
import com.module.bean.User;
import com.module.service.UserService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements UserService
{
    private UserDAO userDao;


    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
    public boolean register(User user){
        boolean result=this.userDao.register(user);
        return result;
    }


    public void update(User user){
        this.userDao.update(user);
    }
    public void delete(int id){
        this.userDao.delete(id);
    }
    public boolean insert(User user){
        return this.userDao.insert(user);
    }
    public int login(User user)
    {
        return this.userDao.login(user.getUsername(),user.getPassword());

    }
    public List<User> userGet(){
        return this.userDao.userGet();
    }
    public User query(String username){
        return this.userDao.query(username);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,isolation = Isolation.READ_COMMITTED)
    public void updatescore(String username){
         this.userDao.updatescore(username);
    }
}
