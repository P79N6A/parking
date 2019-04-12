package com.zhuyitech.sms.constant;


/**
 * Rabbit配置常量
 *
 * @author walkman
 */
public final class RabbitProperties {

    public static final String RABBITMQ_HOST_KEY = "rabbit.host";

    public static final String RABBITMQ_PORT_KEY = "rabbit.port";

    public static final String RABBITMQ_USERNAME_KEY = "rabbit.username";

    public static final String RABBITMQ_PASSWORD_KEY = "rabbit.password";

    public static final String RABBITMQ_VHOST_KEY = "rabbit.vshot";

    /**
     * 短信发送
     */
    public static final String ZHUYITECH_SMS_EXCHANGE = "zhuyitech.sms.topic";

    public static final String ZHUYITECH_SMS_ROUTINGKEY = "zhuyitech.sms.*";

    public static final String ZHUYITECH_SMS_ROUTINGKEY_FAIL = "zhuyitech.sms.failure";

    public static final String ZHUYITECH_SMS_QUEUE_NAME = "zhuyitech.sms";

    public static final String ZHUYITECH_SMS_QUEUE_NAME_FAIL = "zhuyitech.sms.failure";

}
