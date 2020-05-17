package com.module.service.serviceImpl;

/**
 * Created by piglet on 2016/6/13.
 */

import com.module.dao.StatisticDAO;
import com.module.service.StatisticService;
import com.module.dao.daoimpl.StatisticDAOImpl;

import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.List;

@WebService(endpointInterface= "com.module.service.StatisticService",serviceName="StatisticService")
public class StatisticServiceImpl implements StatisticService {



    public List<Integer> statisticone(String keyword,String thevalue){
        StatisticDAO sd=new StatisticDAOImpl();
        return sd.statisticone(keyword,thevalue);
    }
    public List<Integer> statistictwo(String start, String end){
        StatisticDAO sd=new StatisticDAOImpl();
        Timestamp s=Timestamp.valueOf(start);
        Timestamp e=Timestamp.valueOf(end);
        return sd.statistictwo(s,e);
    }



}
