package com.module.dao.daoimpl;

/**
 * Created by piglet on 2016/6/11.
 */
import com.module.bean.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import com.module.bean.Book;
import org.hibernate.SQLQuery;
import com.module.dao.BookDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;
public class BookDAOImpl extends HibernateDaoSupport implements BookDAO
{
    @SuppressWarnings("unchecked")
//    @Override

    private SessionFactory sessionFactory;

    public void delete(int id) {
        String sql = "from Book as book where book.id='" + id + "'";
        List<Book> result = (List<Book>) this.getHibernateTemplate().find(sql);
        Book temp = result.get(0);
        this.getHibernateTemplate().delete(temp);
        Jedis jedis = new Jedis("localhost");
        List<String> strbooks=jedis.lrange("books",0,-1);
        for(int i=0;i<strbooks.size();++i){
            JsonReader reader = Json.createReader(new StringReader(strbooks.get(i)));
            JsonObject parsed = reader.readObject();
            if(parsed.getInt("id")==id){
                JsonObject model = Json.createObjectBuilder()
                        .add("id",-1)
                        .add("photopath",temp.getPhotopath())
                        .add("bookname",temp.getBookname())
                        .add("price",String.valueOf(temp.getPrice()))
                        .add("category",temp.getCategory())
                        .build();
                StringWriter stWriter = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(stWriter);
                jsonWriter.writeObject(model);
                jedis.lset("books",i,stWriter.toString());
            }
        }
    }
    public void update(Book book){
        String sql = "from Book as book where book.id='"+book.getId()+"'";
        List<Book> result=(List<Book>) this.getHibernateTemplate().find(sql);
        Book temp=result.get(0);
        temp.setBookname(book.getBookname());
        temp.setCategory(book.getCategory());
        temp.setDescription(book.getDescription());
        temp.setPhotopath(book.getPhotopath());
        temp.setPrice(book.getPrice());
        temp.setPublishhouse(book.getPublishhouse());
        temp.setStockbalance(book.getStockbalance());
        this.getHibernateTemplate().update(temp);
        Jedis jedis = new Jedis("localhost");
        List<String> strbooks=jedis.lrange("books",0,-1);
        for(int i=0;i<strbooks.size();++i){
            JsonReader reader = Json.createReader(new StringReader(strbooks.get(i)));
            JsonObject parsed = reader.readObject();
            int id=temp.getId();
            if(parsed.getInt("id")==id){
                JsonObject model = Json.createObjectBuilder()
                        .add("id",temp.getId())
                        .add("photopath",temp.getPhotopath())
                        .add("bookname",temp.getBookname())
                        .add("price",String.valueOf(temp.getPrice()))
                        .add("category",temp.getCategory())
                        .add("stockbalance",temp.getStockbalance())
                        .add("publishhouse",temp.getPublishhouse())
                        .add("description",temp.getDescription())
                        .add("mylike",temp.getMylike())
                        .build();
                StringWriter stWriter = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(stWriter);
                jsonWriter.writeObject(model);
                jedis.lset("books",i,stWriter.toString());
            }
        }

    }
    public boolean insert(Book book){
        String sql = "from Book as book where book.bookname='"+book.getBookname()+"'";
        List<Book> result=(List<Book>) this.getHibernateTemplate().find(sql);
        if(result.size()==0){
            this.getHibernateTemplate().save(book);
            result=(List<Book>) this.getHibernateTemplate().find(sql);
            Jedis jedis = new Jedis("localhost");
            List<String>  strbooks = jedis.lrange("books", 0 ,-1);
            if(strbooks.size()!=0){
                JsonObject model = Json.createObjectBuilder()
                        .add("id",result.get(0).getId())
                        .add("photopath",book.getPhotopath())
                        .add("bookname",book.getBookname())
                        .add("price",String.valueOf(book.getPrice()))
                        .add("category",book.getCategory())
                        .add("stockbalance",book.getStockbalance())
                        .add("publishhouse",book.getPublishhouse())
                        .add("description",book.getDescription())
                        .add("mylike",book.getMylike())
                        .build();
                StringWriter stWriter = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(stWriter);
                jsonWriter.writeObject(model);
                jedis.lpush("books", stWriter.toString());
                List<String> debug=jedis.lrange("books",0,-1);
            }
            return true;
        }
        else
            return false;
    }

    public List<Book> bookGet(){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
        String key="books";
        List<String>  strbooks = jedis.lrange(key, 0 ,-1);
        List<Book>result=new ArrayList<Book>();
        if(strbooks.size()==0) {
            String sql = "from Book";
            result = (List<Book>) this.getHibernateTemplate().find(sql);
            for(int i=0;i<result.size();++i) {
                JsonObject model = Json.createObjectBuilder()
                        .add("id",result.get(i).getId())
                        .add("photopath",result.get(i).getPhotopath())
                        .add("bookname",result.get(i).getBookname())
                       .add("price",String.valueOf(result.get(i).getPrice()))
                        .add("category",result.get(i).getCategory())
                        .add("stockbalance",result.get(i).getStockbalance())
                        .add("publishhouse",result.get(i).getPublishhouse())
                        .add("description",result.get(i).getDescription())
                        .add("mylike",result.get(i).getMylike())
                        .build();
                StringWriter stWriter = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(stWriter);
                jsonWriter.writeObject(model);
                jedis.lpush(key, stWriter.toString());
                jedis.expire("books", 24*60*60*1000);
            }
        }
        else{
            for(int i=0;i<strbooks.size();++i){
                JsonReader reader = Json.createReader(new StringReader(strbooks.get(i)));
                JsonObject parsed = reader.readObject();
                if(parsed.getInt("id")==-1)continue;
                Book book=new Book();
                book.setId(parsed.getInt("id"));
                book.setPhotopath(parsed.getString("photopath"));
                book.setBookname(parsed.getString("bookname"));
                String price=parsed.getString("price");
                book.setPrice(Double.parseDouble(parsed.getString("price")));
                book.setCategory(parsed.getString("category"));
                book.setStockbalance(parsed.getInt("stockbalance"));
                book.setPublishhouse(parsed.getString("publishhouse"));
                book.setDescription(parsed.getString("description"));
                book.setMylike(parsed.getInt("mylike"));
                result.add(book);
                System.out.println(parsed.toString());
            }
        }
        return result;
    }
    public Book querybyname(String bookname){
        String sql = "from Book as book where  book.bookname='"+bookname+"'";
        List<Book> result=(List<Book>) this.getHibernateTemplate().find(sql);
        if(result.size()==0){
            Book temp=new Book();
            temp.setId(-1);
            return temp;
        }
        else
            return result.get(0);
    }
    public Book querybyid(int id){
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
                if(parsed.getInt("id")==id) {
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

    public int updatestockbalance(int tmpid,int cartnumber){
        //String querysql = "from Book as book where book.id='"+tmpid+"'";
        //List<Book> result=(List<Book>) this.getHibernateTemplate().find(querysql);
        //sql injection
        String querysql = "from Book as book where book.id=?";
        List<Book> result=(List<Book>) this.getHibernateTemplate().find(querysql,new Integer[]{tmpid});
        Book temp=result.get(0);
        if(temp.getStockbalance()==0){
            return -1;
        }
        int newbalance=temp.getStockbalance()-1;
        int newversion=temp.getVersion()+1;
        Jedis jedis = new Jedis("localhost");
        String key="books";
        List<String> strbooks = jedis.lrange(key, 0 ,-1);
        if(strbooks.size()!=0){
            for(int i=0;i<strbooks.size();++i) {
                JsonReader reader = Json.createReader(new StringReader(strbooks.get(i)));
                JsonObject parsed = reader.readObject();
                if(parsed.getInt("id")==tmpid) {
                    JsonObject model = Json.createObjectBuilder()
                            .add("id", temp.getId())
                            .add("photopath", temp.getPhotopath())
                            .add("bookname", temp.getBookname())
                            .add("stockbalance", newbalance)
                            .add("publishhouse", temp.getPublishhouse())
                            .add("description", temp.getDescription())
                            .add("mylike", temp.getMylike())
                            .add("price", String.valueOf(temp.getPrice()))
                            .add("category", temp.getCategory())
                            .build();
                    StringWriter stWriter = new StringWriter();
                    JsonWriter jsonWriter = Json.createWriter(stWriter);
                    jsonWriter.writeObject(model);
                    jedis.lset(key,i,stWriter.toString());
                    String sql="UPDATE Book SET  stockbalance= '"+newbalance+"',version='"+newversion+"' WHERE id='"+tmpid+"' and version='"+temp.getVersion()+"' ";
                    SQLQuery query = this.getSession().createSQLQuery(sql);
                    int re = query.executeUpdate();
                    if(re==0){
                        return -1;
                    }
                    break;
                }
            }
        }

        return 0;
        //if re==0 rollback()
    }
    public void updatelike(int id){
        String querysql = "from Book as book where book.id='"+id+"'";
        List<Book> result=(List<Book>) this.getHibernateTemplate().find(querysql);
        Book temp=result.get(0);
        Integer newlike=temp.getMylike()+1;
        String sql="UPDATE Book SET  mylike='"+newlike+"' WHERE id='"+id+"' ";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        int re = query.executeUpdate();
        Jedis jedis = new Jedis("localhost");
        String key="books";
        List<String> strbooks = jedis.lrange(key, 0 ,-1);
        if(strbooks.size()!=0){
            for(int i=0;i<strbooks.size();++i) {
                JsonReader reader = Json.createReader(new StringReader(strbooks.get(i)));
                JsonObject parsed = reader.readObject();
                if(parsed.getInt("id")==id) {
                    JsonObject model = Json.createObjectBuilder()
                            .add("id", temp.getId())
                            .add("photopath", temp.getPhotopath())
                            .add("bookname", temp.getBookname())
                            .add("stockbalance", parsed.getInt("stockbalance"))
                            .add("publishhouse", temp.getPublishhouse())
                            .add("description", temp.getDescription())
                            .add("mylike", newlike)
                            .add("price", String.valueOf(temp.getPrice()))
                            .add("category", temp.getCategory())
                            .build();
                    StringWriter stWriter = new StringWriter();
                    JsonWriter jsonWriter = Json.createWriter(stWriter);
                    jsonWriter.writeObject(model);
                    jedis.lset(key,i,stWriter.toString());
                    break;
                }
            }
        }
    }
}

