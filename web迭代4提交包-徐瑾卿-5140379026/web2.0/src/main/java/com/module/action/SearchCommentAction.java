package com.module.action;

import com.module.bean.Comment;
import com.module.lucene.LuceneService;
import com.module.service.CommentService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinqingxu on 17/06/2017.
 */
public class SearchCommentAction {
    private JSONObject jsonresult;
    private LuceneService luceneService;

    public LuceneService getLuceneService() {
        return luceneService;
    }

    public void setLuceneService(LuceneService luceneService) {
        this.luceneService = luceneService;
    }
    public JSONObject getJsonresult() {
        return jsonresult;
    }
    public void setJsonresult(JSONObject jsonresult) {
        this.jsonresult = jsonresult;
    }
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String key = request.getParameter("key");
        List<Comment> result=this.luceneService.searchByTerm("content",key,500);
        Map<String,Object> jsontemp = new HashMap<String,Object>();
        jsontemp.put("total",result.size());
        jsontemp.put("rows", result);
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
