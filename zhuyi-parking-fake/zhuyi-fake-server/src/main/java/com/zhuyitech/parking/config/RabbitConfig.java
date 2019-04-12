package com.zhuyitech.parking.config;

import com.zhuyitech.parking.constant.FakeConstant;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@EnableRabbit
@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(FakeConstant.RABBITMQ_HOST);
        connectionFactory.setPort(FakeConstant.RABBITMQ_PORT);
        connectionFactory.setUsername(FakeConstant.RABBITMQ_USERNAME);
        connectionFactory.setPassword(FakeConstant.RABBITMQ_PASSWORD);
        connectionFactory.setVirtualHost(FakeConstant.RABBITMQ_VHOST);
        //发布确认，template要求CachingConnectionFactory的publisherConfirms属性设置为true
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}