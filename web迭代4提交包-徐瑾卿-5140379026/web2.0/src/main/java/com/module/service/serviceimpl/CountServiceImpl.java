package com.module.service.serviceimpl;

import com.module.service.CountService;

/**
 * Created by piglet on 2017/3/16.
 */
public class CountServiceImpl implements CountService {
    private Integer count;
    public int getCount(){
        if(count==null){
            count=0;
        }
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }

}
