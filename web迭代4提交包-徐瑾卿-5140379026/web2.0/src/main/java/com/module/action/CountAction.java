package com.module.action;

import com.module.service.CountService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by piglet on 2017/3/19.
 */
public class CountAction {
    private CountService countService;
    private JSONObject jsonresult;

    public JSONObject getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(JSONObject jsonresult) {
        this.jsonresult = jsonresult;
    }

    public CountService getCountService() {
        return countService;
    }

    public void setCountService(CountService countService) {
        this.countService = countService;
    }
    public String execute() throws Exception{
        Integer count=countService.getCount();
        countService.setCount(count+1);

        Map result=new HashMap();
        /*request.setAttribute("username",username);
        request.setAttribute("sumprice",sumprice);
        request.setAttribute("sumnumber",sumnumber);
        request.setAttribute("currentdate",currentDate);*/
        result.put("result","success");

        jsonresult = JSONObject.fromObject(result);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonresult);
        out.flush();
        out.close();
        return "success";
    }
}
