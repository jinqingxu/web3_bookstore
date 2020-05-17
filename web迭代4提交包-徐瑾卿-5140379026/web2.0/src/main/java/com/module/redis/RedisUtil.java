package com.module.redis;

import com.module.bean.Book;
import com.module.bean.Order;
import redis.clients.jedis.Jedis;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinqingxu on 19/06/2017.
 */
public class RedisUtil {
    public void addFail(String username, Timestamp cal,int sumnumber,double sumprice){
        Jedis jedis = new Jedis("localhost");
        String key="fail"+username;
        List<String> fails = jedis.lrange(key, 0 ,-1);
        if(fails.size()==0){
            jedis.expire(key, 24*60*60*1000);
        }
        JsonObject model = Json.createObjectBuilder()
                .add("date",cal.toString())
                .add("sumnumber",String.valueOf(sumnumber))
                .add("sumprice",String.valueOf(sumprice))
                .build();
        StringWriter stWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stWriter);
        jsonWriter.writeObject(model);
        jedis.lpush(key, stWriter.toString());
    }
    public List<Order> readfail(String username){
        Jedis jedis = new Jedis("localhost");
        String key="fail"+username;
        List<String>  fails = jedis.lrange(key, 0 ,-1);
        List<Order>result=new ArrayList<Order>();
        for(int i=0;i<fails.size();++i){
            JsonReader reader = Json.createReader(new StringReader(fails.get(i)));
            JsonObject parsed = reader.readObject();
            Order or=new Order();
            or.setDate(Timestamp.valueOf(parsed.getString("date")));
            or.setSumnumber(Integer.valueOf(parsed.getString("sumnumber")));
            or.setSumprice(Double.valueOf(parsed.getString("sumprice")));
            result.add(or);
        }
       return result;
    }

}
