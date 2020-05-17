package soap.publish;

import com.module.service.StatisticService;
import com.module.service.serviceImpl.StatisticServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import javax.xml.ws.Endpoint;

/**
 * Created by jinqingxu on 2017/5/17.
 */
public class WebServiceApp {
    public static void main(String[] args) {
        System.out.println("web service start");
        StatisticServiceImpl implementor= new StatisticServiceImpl();
        String address="http://localhost:8081/StatisticService";
        Endpoint.publish(address, implementor);
        /*JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8081/StatisticService");
        factory.setServiceClass(StatisticService.class);
        factory.setServiceBean(new StatisticServiceImpl());
        factory.create();*/
        System.out.println("web service started");
    }
}
