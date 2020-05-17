package com.module.dao;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by piglet on 2016/6/13.
 */

public interface StatisticDAO {
   public List<Integer> statisticone(String keyword, String thevalue);
   public List<Integer> statistictwo(Timestamp start, Timestamp end);
}
