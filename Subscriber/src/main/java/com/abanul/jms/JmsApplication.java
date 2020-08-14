package com.abanul.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsApplication {

    private static final String QUEUE = "local_queue";

    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication
                .run(JmsApplication.class, args);
    }

}
