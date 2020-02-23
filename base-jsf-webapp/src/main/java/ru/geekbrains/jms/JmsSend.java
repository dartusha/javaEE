package ru.geekbrains.jms;

import ru.geekbrains.persist.Product;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.math.BigDecimal;

@Stateless
public class JmsSend {
    @Resource(lookup = "java:jboss/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/ProductQueue")
    private Queue queue;

    @Path("/jms11")
    @GET
    public String produce() {
        String status = "OK";
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, 0);
            MessageProducer producer = session.createProducer(queue);
            Product product=new Product(100,"Test","Test",new BigDecimal(10));
            ObjectMessage message = session.createObjectMessage();
            message.setObject(product);
            producer.send(message);
        } catch (JMSException e) {
            status = e.getMessage();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    status = e.getMessage();
                }
            }
        }
        return status;
    }
}

