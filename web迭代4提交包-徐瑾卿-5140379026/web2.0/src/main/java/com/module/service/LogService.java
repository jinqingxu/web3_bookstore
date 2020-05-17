package com.module.service;

import com.module.bean.Log;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jinqingxu on 2017/5/16.
 */
public interface LogService {
    public void insert(String username, Timestamp date,String status,String action);
    public List<Log> getAllLog();
}
