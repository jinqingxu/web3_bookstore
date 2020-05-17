package com.module.action;

import com.module.lucene.LuceneService;
import com.module.lucene.LuceneServiceImpl;

import com.module.service.CommentService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by jinqingxu on 14/06/2017.
 */
public class AddCommentAction {
    private CommentService commentService;
    private LuceneService luceneService;
    private JSONObject jsonresult;

    public JSONObject getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(JSONObject jsonresult) {
        this.jsonresult = jsonresult;
    }

    public LuceneService getLuceneService() {
        return luceneService;
    }

    public void setLuceneService(LuceneService luceneService) {
        this.luceneService = luceneService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username=request.getUserPrincipal().getName();
        String content = request.getParameter("content");
        String bookname=request.getParameter("bookname");
        this.commentService.insert(username,bookname,content);
        Map result=new HashMap();
        result.put("message","提交成功！");
        jsonresult = JSONObject.fromObject(result);
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
