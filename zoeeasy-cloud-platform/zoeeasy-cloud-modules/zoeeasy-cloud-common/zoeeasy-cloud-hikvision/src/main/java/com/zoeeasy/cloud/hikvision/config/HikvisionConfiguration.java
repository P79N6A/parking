package com.zoeeasy.cloud.hikvision.config;

import com.zoeeasy.cloud.hikvision.constant.HikvisionPropertyKeys;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @since 2018/11/7 0007
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class HikvisionConfiguration {

    @Value("${" + HikvisionPropertyKeys.HIKVISION_APPKEY + "}")
    private String appKey;

    @Value("${" + HikvisionPropertyKeys.HIKVISION_SECRET + "}")
    private String secret;

    @Value("${" + HikvisionPropertyKeys.HIKVISION_URL_PREFIX + "}")
    private String hikvisionUrlPrefix;

    @Value("${" + HikvisionPropertyKeys.HIKVISION_IP + "}")
    private String hikvisionIp;

    @Value("${" + HikvisionPropertyKeys.HIKVISION_ACTIVEMQ_BROKER_URL_KEY + "}")
    private String brokerURL;

    @Value("${" + HikvisionPropertyKeys.HIKVISION_ACTIVEMQ_TOPIC_DESTINATION_KEY + "}")
    private String topicDestination;

    @Value("${" + HikvisionPropertyKeys.HIKVISION_ACTIVEMQ_USER_NAME_KEY + "}")
    private String userName;

    @Value("${" + HikvisionPropertyKeys.HIKVISION_ACTIVEMQ_AUTHENTICATION_KEY + "}")
    private String password;

}
