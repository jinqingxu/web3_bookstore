package com.module.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.module.bean.Order;
import com.module.service.OrderService;
import com.opensymphony.xwork2.ActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.sql.Timestamp;
import java.util.Map;

/**
 * 消息消费者
 * 
 * @author zhaiyz
 */
public class OrderPlaceServer implements MessageListener {
	private OrderService orderService;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPlaceServer.class);
	private JmsTemplate jmsTemplate;
	public OrderService getorderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * @return the jmsTemplate
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * @param jmsTemplate the jmsTemplate to set
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public void dealwithorder(String me)throws Exception{
		String[] tmpme=me.split("@");
		String username=tmpme[0];
		java.sql.Timestamp currentDate= Timestamp.valueOf(tmpme[1]);
		String[] checked=tmpme[2].split("\\|");
		String[] numbers=tmpme[3].split("\\|");
		String[] tmpbookid=tmpme[4].split("\\|");
		String[] tmpprice=tmpme[5].split("\\|");
		Integer[] bookid=new Integer[tmpbookid.length];
		Double[] price=new Double[tmpprice.length];
		for(int i=0;i<tmpbookid.length;++i){
			bookid[i]=Integer.parseInt(tmpbookid[i]);
			price[i]=Double.parseDouble(tmpprice[i]);
		}

		this.orderService.insertorder(username, currentDate, checked, numbers, bookid, price);

	}
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.print(textMessage.getText());
				
				String me=textMessage.getText();
				dealwithorder(me);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
