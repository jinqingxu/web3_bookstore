package com.module.lucene;

import com.module.bean.Comment;

import java.util.List;

/**
 * Created by jinqingxu on 17/06/2017.
 */
public interface LuceneService {
    public void createIndex();
    public List<Comment> searchByTerm(String field, String keyword, int num);
    public void debug();
}
