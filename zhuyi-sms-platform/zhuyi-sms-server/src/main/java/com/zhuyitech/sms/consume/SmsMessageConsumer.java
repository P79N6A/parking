//package com.zhuyitech.sms.consume;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zhuyitech.sms.constant.RabbitProperties;
//import com.zhuyitech.sms.dto.request.MessageSendRequestDto;
//import com.zhuyitech.sms.message.AmqpMessage;
//import com.zhuyitech.sms.message.SmsCodeSendPayload;
//import com.zhuyitech.sms.service.api.SmsSendService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
///**
// * 消息消费端
// *
// * @author walkman
// */
//public class SmsMessageConsumer implements ChannelAwareMessageListener {
//
//    private static final Logger LOG = LoggerFactory.getLogger(SmsMessageConsumer.class);
//
//    @Autowired
//    private SmsSendService smsSendService;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Override
//    public void onMessage(Message message, Channel channel) throws Exception {
//
//        try {
//
//            LOG.info("##### = {}", new String(message.getBody()));
//
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            AmqpMessage<SmsCodeSendPayload> smsCodeSendPayloadAmqpMessage = objectMapper.readValue(new String(message.getBody()),
//                    new TypeReference<AmqpMessage<SmsCodeSendPayload>>() {
//                    });
//            SmsCodeSendPayload payload = smsCodeSendPayloadAmqpMessage.getPayload();
//            if (payload != null) {
//
//                MessageSendRequestDto messageSendRequestDto = new MessageSendRequestDto();
//                messageSendRequestDto.setClientId(payload.getClientId());
//                messageSendRequestDto.setTemplateId(payload.getTemplateId());
//                messageSendRequestDto.setPhoneNumber(payload.getPhoneNumber());
//                messageSendRequestDto.setVerifyType(payload.getVerifyType());
//                messageSendRequestDto.setParameters(payload.getParameters());
//                smsSendService.sendVerifySms(messageSendRequestDto);
//            }
//            //手动消息应答
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//
//            LOG.error(e.getMessage(), e);
//
//            //true 重新放入队列
////            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
//            //对于处理不了的异常消息
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            ObjectMapper objectMapper = new ObjectMapper();
//            AmqpMessage<SmsCodeSendPayload> smsCodeSendPayloadAmqpMessage = objectMapper.readValue(new String(message.getBody()),
//                    new TypeReference<AmqpMessage<SmsCodeSendPayload>>() {
//                    });
//            //发送到失败队列
//            rabbitTemplate.convertAndSend(RabbitProperties.ZHUYITECH_SMS_EXCHANGE, RabbitProperties.ZHUYITECH_SMS_ROUTINGKEY_FAIL, smsCodeSendPayloadAmqpMessage);
//        }
//    }
//}