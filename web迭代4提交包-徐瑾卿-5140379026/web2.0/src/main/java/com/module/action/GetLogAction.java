package com.module.action;

import com.module.bean.Comment;
import com.module.bean.Log;
import com.module.service.CommentService;
import com.module.service.LogService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinqingxu on 18/06/2017.
 */
public class GetLogAction {
    private JSONObject jsonresult;
    private LogService logService;

    public JSONObject getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(JSONObject jsonresult) {
        this.jsonresult = jsonresult;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public LogService getLogService() {
        return logService;
    }

    public String execute() throws Exception {
        List<Log> result=this.logService.getAllLog();
        Map<String,Object> jsontemp = new HashMap<String,Object>();
        jsontemp.put("total",result.size());
        jsontemp.put("rows", result);
        List<String>times=new ArrayList<String>();
        for(int i=0;i<result.size();++i){
            times.add(result.get(i).getDate().toString());

        }
        jsontemp.put("times",times);
        JsonConfig jsonConfig=new JsonConfig();
        jsonresult = JSONObject.fromObject(jsontemp,jsonConfig);
        String debuf=jsonresult.toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(jsonresult);
        out.flush();
        out.close();
        //luceneService.createIndex();
        return "success";
    }
}
