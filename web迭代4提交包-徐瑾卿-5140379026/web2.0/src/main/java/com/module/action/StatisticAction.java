package com.module.action;




import com.soap.client.StatisticService;

import net.sf.json.JSONObject;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by piglet on 2016/6/13.
 */
public class StatisticAction {
    private Date start;
    private Date end;
    private JSONObject jsonresult;
    //private StatisticService statisticService;
    public Date getStart(){
        return start;
    }
    public void setStart(Date start){
        this.start=start;
    }
    public void  setEnd(Date end){
        this.end=end;
    }
    public Date getEnd(){
        return end;
    }
    public void setJsonresult(JSONObject jsonresult){
        this.jsonresult = jsonresult;
    }
    public JSONObject getJsonresult(){
        return jsonresult;
    }
    /*public StatisticService getStatisticService() {
        return statisticService;
    }*/
    /*public void setStatisticService(StatisticService statisticService) {
        this. statisticService= statisticService;
    }*/
    public static List<Integer> soap2(String keyword,String thevalue){
        List<Integer>result=null;
        try {
            String wsdlUrl="http://localhost:8081/StatisticService?wsdl";
            String ns="http://serviceImpl.service.module.com/";
            // 创建服务service
            URL url = new URL(wsdlUrl);
            QName sname= new QName(ns,"StatisticService");
            Service service = Service.create(url,sname);

            //创建DIspatch
            Dispatch<SOAPMessage> dispatch=service.createDispatch(new QName(ns,"StatisticServiceImplPort"),
                    SOAPMessage.class, Service.Mode.MESSAGE);

            //创建SOAPMessage
            SOAPMessage msg=MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope =msg.getSOAPPart().getEnvelope();
            SOAPBody body=envelope.getBody();

            //创建QName来指定消息中传递数据
            QName ename=new QName(ns,"statisticone","StatisticService");
            //<nn:add xmlns="xx"/>
            SOAPBodyElement ele=body.addBodyElement(ename);
            ele.addChildElement("keyword").setValue(keyword);
            ele.addChildElement("thevalue").setValue(thevalue);
            msg.writeTo(System.out);
            System.out.println("\n invoking....");

            //通过Dispatch传递消息,会返回相应消息
            SOAPMessage response = dispatch.invoke(msg);
            response.writeTo(System.out);
            System.out.println();

            //将相应的消息转换为doc对象
            Document doc= response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
            String str=doc.getElementsByTagName("addResult").item(0).getTextContent();
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static StatisticService soap(){
        //wsdl网络路径
        URL url=null;
        try {
            url = new URL("http://localhost:8081/StatisticService?wsdl");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String ns="http://serviceImpl.service.module.com/";
        QName qName = new QName(ns, "StatisticService");
        //创建服务对象
        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qName);
        //获得Hiservice的实现类对象
        StatisticService statisticService = service.getPort(new QName(ns,"StatisticServiceImplPort"),StatisticService.class);
      return statisticService;
    }

    public String statisticone()throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String  keyword= request.getParameter("keyword");
        String thevalue=request.getParameter("thevalue");
        //soap
        StatisticService ss=soap();
        List<Integer> result=ss.statisticone(keyword,thevalue);
        Map<String,Integer>returnvalue=new HashMap();
        returnvalue.put("sumprice",result.get(0));
        returnvalue.put("sumorder",result.get(1));
        returnvalue.put("sumnumber",result.get(2));
        jsonresult = JSONObject.fromObject(returnvalue);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(jsonresult);
            out.flush();
            out.close();
            }
            catch(Exception e){e.printStackTrace();}

        return "success";
    }
    public String statistictwo()throws Exception{

        HttpServletRequest request = ServletActionContext.getRequest();
        String  strstart= request.getParameter("start");
        String strend=request.getParameter("end");
        strstart+=" 00:00:00";
        strend+=" 00:00:00";
        StatisticService ss=soap();
        List<Integer> result=ss.statistictwo(strstart,strend);
        Timestamp start=Timestamp.valueOf(strstart);
        Timestamp end=Timestamp.valueOf(strend);
        /*java.util.Date st = null;
        java.util.Date en=null;
        try {
            st= format.parse(strstart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            en= format.parse(strend);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Timestamp start = new java.sql.Timestamp(st.getTime());
        java.sql.Timestamp end=new java.sql.Timestamp(en.getTime());*/

        //List<Integer>result=  this.statisticService.statistictwo(start,end);
        //List<Integer> result=null;
        Map<String,Integer>returnvalue=new HashMap();
        returnvalue.put("sumprice",result.get(0));
        returnvalue.put("sumorder",result.get(1));
        returnvalue.put("sumnumber",result.get(2));
        jsonresult = JSONObject.fromObject(returnvalue);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(jsonresult);
            out.flush();
            out.close();
        }  catch(Exception e){e.printStackTrace();}

        return "success";
    }
}
