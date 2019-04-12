package com.zoeeasy.cloud.hikvision.constant;


/**
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
public interface HikvisionPropertyKeys {

    /**
     * hikvison的Appkey
     */
    String HIKVISION_APPKEY = "hikvision.appkey";

    /**
     * hikvison的秘钥Secret
     */
    String HIKVISION_SECRET = "hikvision.secret";

    /**
     * hikvision的ip
     */
    String HIKVISION_IP = "hikvision.ip";

    /**
     * hikvision接口调用前缀
     */
    String HIKVISION_URL_PREFIX = "hikvision.url.prefix";

    String HIKVISION_BROKER_SUBSCRIBE = "hikvision.broker.subscribe";

    String HIKVISION_ACTIVEMQ_BROKER_URL_KEY = "hikvision.activemq.brokerURL";

    String HIKVISION_ACTIVEMQ_TOPIC_DESTINATION_KEY = "hikvision.activemq.topicDestination";

    String HIKVISION_ACTIVEMQ_USER_NAME_KEY = "hikvision.activemq.userName";

    String HIKVISION_ACTIVEMQ_AUTHENTICATION_KEY = "hikvision.activemq.password";
}
