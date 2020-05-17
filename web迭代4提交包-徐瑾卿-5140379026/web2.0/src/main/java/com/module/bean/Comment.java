package com.module.bean;

/**
 * Created by jinqingxu on 17/06/2017.
 */
public class Comment {
    private int id;
    private String username;
    private String bookname;
    private String content;

    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getBookname() {
        return bookname;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
