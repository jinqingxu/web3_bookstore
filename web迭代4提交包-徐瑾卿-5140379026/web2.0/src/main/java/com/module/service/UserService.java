package com.module.service;
import com.module.bean.User;
import java.util.List;
public interface UserService
{

    public int login(User user);
    public boolean register(User user);
    public void update(User user);
    public boolean insert(User user);
    public List<User> userGet();
    public User query(String username);
    public void delete(int id);
    public void updatescore(String username);


}
