package com.module.service.serviceimpl;

import com.module.dao.PayDao;
import com.module.service.PayService;

/**
 * Created by jinqingxu on 2017/5/15.
 */
public class PayServiceImpl implements PayService {
    private PayDao payDao;

    public PayDao getPayDao() {
        return payDao;
    }

    public void setPayDao(PayDao payDao) {
        this.payDao = payDao;
    }

    public String pay(String username, String password)throws  Exception{
        return this.payDao.checkpay(username,password);
    }
}
