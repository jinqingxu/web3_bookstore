package com.module.service;
import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.List;
/**
 * Created by piglet on 2016/6/13.
 */

@WebService
public interface StatisticService {
   public List<Integer> statisticone(String keyword, String thevalue);
   public List<Integer> statistictwo(String start, String end);

}
