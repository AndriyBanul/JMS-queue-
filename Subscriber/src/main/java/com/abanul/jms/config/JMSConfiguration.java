package com.abanul.jms.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.Arrays;

@Configuration
@EnableJms
public class JMSConfiguration implements JmsListenerConfigurer
{
	@Bean
	public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter( messageConverter());
		return factory;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		factory.setTrustedPackages(Arrays.asList("com.abanul.jms"));
		return factory;
	}

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(handlerMethodFactory());
	}


	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
		return new JmsTemplate(connectionFactory);
	}

}

