package com.module.thread;

import com.module.lucene.LuceneService;

import java.util.TimerTask;

/**
 * Created by jinqingxu on 17/06/2017.
 */
public class Task extends TimerTask {
    private LuceneService luceneService;

    public void setLuceneService(LuceneService luceneService) {
        this.luceneService = luceneService;
    }

    public LuceneService getLuceneService() {
        return luceneService;
    }

    public void run() {
        luceneService.createIndex();
    }
}