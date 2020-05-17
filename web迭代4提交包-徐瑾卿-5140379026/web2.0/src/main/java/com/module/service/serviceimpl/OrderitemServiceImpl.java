package com.module.service.serviceimpl;
import com.module.dao.BookDAO;
import com.module.dao.OrderDAO;
import com.module.dao.OrderitemDAO;
import com.module.service.OrderitemService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by piglet on 2016/6/12.
 */
public class OrderitemServiceImpl  implements OrderitemService {
    private OrderitemDAO orderitemDao;
    public OrderitemDAO getOrderitemDao() {
        return orderitemDao;
    }

    public void setOrderitemDao(OrderitemDAO orderitemDao) {
        this.orderitemDao = orderitemDao;
    }

    @Transactional(propagation= Propagation.MANDATORY,isolation = Isolation.READ_COMMITTED)
    public void insert(String orderid,int tmpid,int cartnumber){
        this.orderitemDao.insert(orderid,tmpid,cartnumber);
    }
}
