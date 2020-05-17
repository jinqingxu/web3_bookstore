package com.module.dao;

import java.util.List;
import com.module.bean.User;
public interface UserDAO
{
    public int login(String username,String password);
    public boolean register(User user);
    public void update(User user);
    public boolean insert(User user);
    public List<User> userGet();
    public User query(String username);
    public void delete(int id);
    public void updatescore(String username);

}
