package com.module.demo;

/**
 * Created by jinqingxu on 2017/5/17.
 */

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WebServiceClient {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
        svr.setServiceClass(HelloWorld.class);
        svr.setAddress("http://localhost:8080/helloWorld");
        HelloWorld hw = (HelloWorld) svr.create();
        User user = new User();
        user.setName("Tony");
        System.out.println(hw.sayHiToUser(user));
    }
}
