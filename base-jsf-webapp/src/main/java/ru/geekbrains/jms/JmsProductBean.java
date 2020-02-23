package ru.geekbrains.jms;


import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {@ActivationConfigProperty(
        propertyName = "destinationType",propertyValue = "javax.jms.Queue"
),
        @ActivationConfigProperty(propertyName = "destination",propertyValue =
                "java:/jms/queue/ProductQueue"),
        @ActivationConfigProperty(propertyName = "messageSelector",
                propertyValue = "action='create'")
}
)
public class JmsProductBean implements MessageListener {
    @EJB
    private ProductRepository productRepository;
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage){
            try {
                Product product=(Product)((ObjectMessage) message).getObject();
                productRepository.insert(product);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
