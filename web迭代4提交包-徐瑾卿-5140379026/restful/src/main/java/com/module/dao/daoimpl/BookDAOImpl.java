package com.module.dao.daoimpl;

/**
 * Created by piglet on 2016/6/11.
 */

import com.module.bean.Book;
import com.module.dao.BookDAO;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import redis.clients.jedis.Jedis;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl extends HibernateDaoSupport implements BookDAO
{
    @SuppressWarnings("unchecked")
//    @Override

    private SessionFactory sessionFactory;
    public Book querybyname(String bookname){

        /*String sql = "from Book as book where  book.bookname='"+bookname+"'";
        List<Book> result=(List<Book>) this.getHibernateTemplate().find(sql);
        if(result.size()==0){
            Book temp=new Book();
            temp.setId(-1);
            return temp;
        }
        else
            return result.get(0);*/

        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        String key="books";
        List<String> strbooks = jedis.lrange(key, 0 ,-1);
        Book rebook=new Book();
        if(strbooks.size()==0) {
            System.out.println("error");
        }
        else{
            for(int i=0;i<strbooks.size();++i) {
                JsonReader reader = Json.createReader(new StringReader(strbooks.get(i)));
                JsonObject parsed = reader.readObject();
                if(parsed.getString("bookname").equals(bookname)) {
                    rebook.setId(parsed.getInt("id"));
                    rebook.setPhotopath(parsed.getString("photopath"));
                    rebook.setBookname(parsed.getString("bookname"));
                    rebook.setStockbalance(parsed.getInt("stockbalance"));
                    rebook.setPublishhouse(parsed.getString("publishhouse"));
                    rebook.setDescription(parsed.getString("description"));
                    rebook.setMylike(parsed.getInt("mylike"));
                    rebook.setPrice(Double.parseDouble(parsed.getString("price")));
                    rebook.setCategory(parsed.getString("category"));
                    break;
                }
            }
        }
        return rebook;
    }

}

