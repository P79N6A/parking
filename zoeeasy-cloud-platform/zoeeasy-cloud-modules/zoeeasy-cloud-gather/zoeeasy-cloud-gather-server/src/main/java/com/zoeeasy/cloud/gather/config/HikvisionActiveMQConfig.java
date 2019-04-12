package com.zoeeasy.cloud.gather.config;

import com.zoeeasy.cloud.gather.consumer.HikMessageConsumer;
import com.zoeeasy.cloud.hikvision.config.HikvisionConfiguration;
import com.zoeeasy.cloud.hikvision.constant.HikvisionPropertyKeys;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * 海康消息队列配置
 *
 * @author wh
 * @since : 2018/7/21
 */
@ConditionalOnProperty(
        value = HikvisionPropertyKeys.HIKVISION_BROKER_SUBSCRIBE,
        matchIfMissing = false
)
@Configuration
public class HikvisionActiveMQConfig {

    @Autowired
    private HikvisionConfiguration hikvisionConfiguration;

    @Bean
    public ConnectionFactory activeMQConnectionFactory() {

        return new ActiveMQConnectionFactory(hikvisionConfiguration.getUserName(),
                hikvisionConfiguration.getPassword(), hikvisionConfiguration.getBrokerURL());
    }

    @Bean
    HikMessageConsumer hikMessageConsumer() {
        return new HikMessageConsumer();
    }

    /**
     * 消息监听容器
     *
     * @return
     */
    @Bean
    DefaultMessageListenerContainer messageListenerContainer() {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(activeMQConnectionFactory());
        defaultMessageListenerContainer.setMessageListener(hikMessageConsumer());
        defaultMessageListenerContainer.setDestination(activeMQTopic());
        return defaultMessageListenerContainer;
    }

    /**
     * 队列目的地
     *
     * @return
     */
    @Bean
    ActiveMQTopic activeMQTopic() {
        return new ActiveMQTopic(hikvisionConfiguration.getTopicDestination());
    }
}
