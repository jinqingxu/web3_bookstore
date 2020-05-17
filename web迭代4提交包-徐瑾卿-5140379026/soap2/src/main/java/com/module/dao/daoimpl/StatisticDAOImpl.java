package com.module.dao.daoimpl;

import com.module.dao.StatisticDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piglet on 2016/6/13.
 */
public class StatisticDAOImpl implements StatisticDAO {
    public List<Integer> statisticone(String keyword,String thevalue) {
        System.out.println("arrive");
        if(keyword==null)System.out.println("keywordnull");
        if(thevalue==null)System.out.println("thevaluenull");
        List<Integer> result=new ArrayList<Integer>();
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";
        final String DB_URL="jdbc:mysql://localhost/mydb";
        //  数据库的凭据
        final String USER = "root";
        final String PASS = "xu9659";
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(keyword.equals("username")) {
            try {
                System.out.println(thevalue);
                String procedure = "{call statisticbyuser (?,?,?,?) }";
                CallableStatement cstmt = conn.prepareCall(procedure);
                cstmt.setString(1, thevalue);
                Integer sumprice=1;
                Integer sumnumber=1;
                Integer sumorder=1;
                ResultSet rs = cstmt.executeQuery();
                        while(rs.next()){
                               Double tmpprice= rs.getDouble("sumprice");
                                sumprice=tmpprice.intValue();
                                sumorder=rs.getInt("sumorder");
                               sumnumber=rs.getInt("sumnumber");
                           }
                result.add(sumprice);
                result.add(sumorder);
                result.add(sumnumber);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        }
        if(keyword.equals("bookname")){
            try {
                String procedure = "{call  statisticbybook(?,?,?,?) }";
                CallableStatement cstmt = conn.prepareCall(procedure);
                cstmt.setString(1, thevalue); //设置参数
                Integer sumprice=1;
                Integer sumnumber=1;
                Integer sumorder=1;
                ResultSet rs = cstmt.executeQuery();
                while(rs.next()){
                    Double tmpprice= rs.getDouble("sumprice");
                    sumprice=tmpprice.intValue();
                    sumorder=rs.getInt("sumorder");
                    sumnumber=rs.getInt("sumnumber");
                }
                result.add(sumprice);
                result.add(sumorder);
                result.add(sumnumber);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        if(keyword.equals("category")){
            try {

                String procedure = "{call statisticbycategory (?,?,?,?) }";
                CallableStatement cstmt = conn.prepareCall(procedure);
                cstmt.setString(1, thevalue); //设置参数
                Integer sumprice=1;
                Integer sumnumber=1;
                Integer sumorder=1;
                ResultSet rs = cstmt.executeQuery();
                while(rs.next()){
                    Double tmpprice= rs.getDouble("sumprice");
                    sumprice=tmpprice.intValue();
                    sumorder=rs.getInt("sumorder");
                    sumnumber=rs.getInt("sumnumber");
                }
                result.add(sumprice);
                result.add(sumorder);
                result.add(sumnumber);

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        }

        return result;
    }
    public List<Integer> statistictwo(Timestamp start,Timestamp end){

        List<Integer> result=new ArrayList<Integer>();
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";
        final String DB_URL="jdbc:mysql://localhost/mydb";
        //  数据库的凭据
        final String USER = "root";
        final String PASS = "xu9659";
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
        }
        catch(Exception e){
            e.printStackTrace();
        }

            try {
               System.out.println(start);
                System.out.println(end);
                String procedure = "{call statisticbydate (?,?,?,?,?) }";
                CallableStatement cstmt = conn.prepareCall(procedure);
                cstmt.setTimestamp(1, start); //设置参数
                cstmt.setTimestamp(2, end);
                //cstmt.registerOutParameter(3, Types.INTEGER);
                //cstmt.registerOutParameter(4, Types.INTEGER);
                //cstmt.registerOutParameter(5, Types.INTEGER);
                //cstmt.execute(); //调用存储过程
                //Integer sumprice = cstmt.getInt(3);//获取输出参数
                //Integer sumorder = cstmt.getInt(4);//获取输出参数
                //Integer sumnumber = cstmt.getInt(5);//获取输出参数
                Integer sumprice=1;
                Integer sumnumber=1;
                Integer sumorder=1;
                ResultSet rs = cstmt.executeQuery();
                while(rs.next()){
                    Double tmpprice= rs.getDouble("sumprice");
                    sumprice=tmpprice.intValue();
                    sumorder=rs.getInt("sumorder");
                    sumnumber=rs.getInt("sumnumber");
                }
                result.add(sumprice);
                result.add(sumorder);
                result.add(sumnumber);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        return result;
    }
}
