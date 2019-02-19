package com.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducter {
    private static final String Url = "tcp://127.0.0.1:61616";
    private static final String queuename = "test-queue";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(Url);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination= session.createQueue(queuename);
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 100 ; i++) {
            TextMessage textMessage = session.createTextMessage("test" + i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
    }
}
