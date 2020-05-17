package com.module.aop;

/**
 * Created by jinqingxu on 2017/5/15.
 */
import com.module.service.LogService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Aspect
@Order(1)
public class AspectJAdvice {
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* com.module.service.serviceimpl.OrderServiceImpl.insertorder(..))" +
            "||execution(* com.module.service.serviceimpl.PayServiceImpl.pay(..))")
    private void aspectJMethod(){};

    @AfterReturning(pointcut = "aspectJMethod()", returning = "result")
    public void after(JoinPoint pjp,String result){
        String action=pjp.getSignature().getName();
        java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());
        Object[] obj=   pjp.getArgs();
        String username=obj[0].toString();
        String status="success";
         if(result!=null){
             status=result;
         }
        if(this.logService!=null)this.logService.insert(username,currentDate,status,action);
    }
    @AfterThrowing("aspectJMethod()")
    public void afterthrowing(JoinPoint pjp){
        String action=pjp.getSignature().getName();
        java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());
        Object[] obj=   pjp.getArgs();
        String username=obj[0].toString();
        String status="fail";
        if(this.logService!=null)this.logService.insert(username,currentDate,status,action);

    }

}

