package com.abanul.jms.receiver;

import com.abanul.jms.model.Product;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final String MESSAGE_QUEUE = "message_queue";

    @JmsListener(destination = MESSAGE_QUEUE)
    public void receiveMessage(Product product) {
        System.out.println("Received " + product);
    }

}
