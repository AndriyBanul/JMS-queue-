package com.abanul.jms.producer;

import com.abanul.jms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String MESSAGE_QUEUE = "message_queue";

    @GetMapping
    public void get() {
        Product product = new Product();
        product.setProductId(1);
        product.setName("Laptop");
        product.setQuantity(10 + 1);

        // Send a message
        System.out.println("Sending a product " + 1);
        jmsTemplate.convertAndSend(MESSAGE_QUEUE, product);
    }
}
