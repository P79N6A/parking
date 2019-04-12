package com.zhuyitech.parking.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class CustomMessageSender {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CustomMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    private final Sequence idWorker = new Sequence(0, 0);

    @Scheduled(fixedDelay = 300000L)
    public void sendMessage() {
//        for (int i = 0; i < 500; i++) {
//            Long messageId = idWorker.nextId();
//            final CustomMessage message = new CustomMessage(messageId, "Hello World!", new Random().nextInt(50), false);
//            log.info("Sending message...,message body {}" + message.toString());
//            rabbitTemplate.convertAndSend(Constant.EXCHANGE_NAME, "sinotopia.message.specific", message);
//        }
    }
}
