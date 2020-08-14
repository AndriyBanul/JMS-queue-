package com.abanul.jms.config;

import com.abanul.jms.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ProductMessageConverter implements MessageConverter {

    ObjectMapper mapper;

    public ProductMessageConverter() {
        mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session)
            throws JMSException {
        Product person = (Product) object;
        String payload = null;
        try {
            payload = mapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
        }

        TextMessage message = session.createTextMessage();
        message.setText(payload);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();

        Product person = null;
        try {
            person = mapper.readValue(payload, Product.class);
        } catch (Exception e) {
        }

        return person;
    }
}
