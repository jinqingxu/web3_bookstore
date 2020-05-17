package com.module.dao.daoimpl;

import com.module.dao.PayDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by jinqingxu on 2017/5/15.
 */
public class PayDaoImpl implements PayDao {
    public String checkpay(String username,String password){
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
            Statement stmt = conn.createStatement();
            String sql;
            sql = "select * from bank where username='" + username + "' ";
            ResultSet rs = stmt.executeQuery(sql);
            boolean wrong = true;
            while (rs.next()) {
                if (rs != null) {
                    String realpwd = rs.getString("password");
                    if (realpwd.equals(password)) {

                        return "success";
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "fail";
    }
}
