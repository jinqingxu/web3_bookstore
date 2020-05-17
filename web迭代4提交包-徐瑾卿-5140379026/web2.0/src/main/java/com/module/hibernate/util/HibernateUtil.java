package com.module.hibernate.util;

/**
 * Created by piglet on 2016/6/12.
 */
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * 私有的构造方法，这样就可以防止实例化这个类
     */
    private HibernateUtil(){}

    /**
     * 因为读取配置文件和映射文件很耗时间，所以这段代码块定义成静态的，那么只加载一次就可以了
     */
    static{
        Configuration config=new Configuration();
        config.configure();//读取配置文件，默认的是读取hibernate.cfg.xml，若文件名不是这个，也可以以参数形式指定
        sessionFactory=config.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
