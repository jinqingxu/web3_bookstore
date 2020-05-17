package com.module.dao.daoimpl;


import com.module.bean.Comment;
import com.module.bean.Log;

import java.sql.Timestamp;
import java.util.List;

import com.module.dao.LogDAO;
import com.module.dao.OrderDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
/**
 * Created by jinqingxu on 2017/5/16.
 */
public class LogDAOImpl  extends HibernateDaoSupport implements LogDAO {
    public void insert(String username, Timestamp date, String status, String action){
       Log log=new Log();
       log.setAction(action);
       log.setDate(date);
       log.setStatus(status);
       log.setUsername(username);
       this.getHibernateTemplate().save(log);
    }

    public List<Log> getAllLog() {
        String sql = "from Log as log";
        List<Log> result=(List<Log>) this.getHibernateTemplate().find(sql);
        if(result.size()>0){
            return result;
        }
        else{
            return null;
        }
    }
}
