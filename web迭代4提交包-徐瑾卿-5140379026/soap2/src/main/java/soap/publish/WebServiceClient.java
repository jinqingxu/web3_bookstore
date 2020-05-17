package soap.publish;


import com.module.service.StatisticService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.List;

/**
 * Created by jinqingxu on 2017/5/17.
 */
public class WebServiceClient {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
        svr.setServiceClass(StatisticService.class);
        svr.setAddress("http://localhost:8081/StatisticService");
        StatisticService st = (StatisticService) svr.create();
        String keyword="username";
        String thevalue="fname1";
        List<Integer> result=st.statisticone(keyword,thevalue);
    }
}
