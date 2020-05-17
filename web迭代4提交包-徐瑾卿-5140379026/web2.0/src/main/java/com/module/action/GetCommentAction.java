package com.module.action;

import com.module.bean.Book;
import com.module.bean.Comment;
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
public class GetCommentAction {
    private JSONObject jsonresult;
    private CommentService commentService;

    public JSONObject getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(JSONObject jsonresult) {
        this.jsonresult = jsonresult;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public String execute() throws Exception {
        List<Comment> result=this.commentService.getAllComment();
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
