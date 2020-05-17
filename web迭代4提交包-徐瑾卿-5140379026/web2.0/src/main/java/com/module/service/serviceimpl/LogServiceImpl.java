package com.module.service.serviceimpl;

import com.module.bean.Log;
import com.module.dao.LogDAO;
import com.module.service.LogService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jinqingxu on 2017/5/16.
 */
public class LogServiceImpl implements LogService {
    private LogDAO logDao;

    public LogDAO getLogDao() {
        return logDao;
    }

    public void setLogDao(LogDAO logDao) {
        this.logDao = logDao;
    }

    public void insert(String username, Timestamp date,String status, String action){
       this.logDao.insert(username,date,status,action);
    }

    public List<Log> getAllLog() {
        return this.logDao.getAllLog();
    }
}
