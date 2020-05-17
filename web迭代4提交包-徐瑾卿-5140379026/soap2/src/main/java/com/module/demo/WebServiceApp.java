package com.module.demo;

import javax.xml.ws.Endpoint;
/**
 * Created by jinqingxu on 2017/5/17.
 */
public class WebServiceApp {
    public static void main(String[] args) {
        System.out.println("web service start");
        HelloWoldImpl implementor= new HelloWoldImpl();
        String address="http://localhost:8080/helloWorld";
        Endpoint.publish(address, implementor);
        System.out.println("web service started");
    }
}
