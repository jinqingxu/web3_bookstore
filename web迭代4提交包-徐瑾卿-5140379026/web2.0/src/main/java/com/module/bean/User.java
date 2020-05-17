package com.module.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
public class User
{
    private String phone;
    private String email;
    private int id;
    private String username;
    private String password;

    private int  roleid;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoleid(){return roleid;}

    public void setRoleid(int roleid){this.roleid=roleid;}

    public String getPhone(){return phone;}

    public void setPhone(String phone){this.phone=phone;}

    public String getEmail(){return email;}

    public void setEmail(String email){this.email=email;}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
