package com.zhuyitech.parking.tool.config;

import com.zhuyitech.parking.tool.constant.JPushPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * jpush配置属性
 *
 * @author zwq
 */
@Component
public class JPushConfig {

    @Value("${" + JPushPropertyKeys.JIGUANG_JPUSH_APP_KEY + ":}")
    @Getter
    @Setter
    private String appkey;

    @Value("${" + JPushPropertyKeys.JIGUANG_JPUSH_MASTER_SECRET_KEY + ":}")
    @Getter
    @Setter
    private String mastersecrete;

    @Value("${" + JPushPropertyKeys.JIGUANG_JPUSH_APNSPRODUCTION_KEY + ":}")
    @Getter
    @Setter
    private Boolean apnsproduction;

    @Value("${" + JPushPropertyKeys.JIGUANG_JPUSH_TTL_KEY + ":43200}")
    @Getter
    @Setter
    private String ttl;

    @Value("${" + JPushPropertyKeys.NOTIFICATION_MESSAGE_TEMPLATE_NEW_ORDER_KEY + ":}")
    @Getter
    @Setter
    private String messageNewOrder;

    @Value("${" + JPushPropertyKeys.NOTIFICATION_MESSAGE_TEMPLATE_ORDER_PAYED_KEY + ":}")
    @Getter
    @Setter
    private String messageOrderPayed;

    @Value("${" + JPushPropertyKeys.NOTIFICATION_PUSH_TEMPLATE_PARKING_ENTER_KEY + ":}")
    @Getter
    @Setter
    private String pushParkingEnter;

    @Value("${" + JPushPropertyKeys.NOTIFICATION_PUSH_TEMPLATE_NEW_ORDER_KEY + ":}")
    @Getter
    @Setter
    private String pushNewOrder;

    @Value("${" + JPushPropertyKeys.NOTIFICATION_PUSH_TEMPLATE_ORDER_PAYED_KEY + ":}")
    @Getter
    @Setter
    private String pushOrderPayed;

    @Value("${" + JPushPropertyKeys.NOTIFICATION_PUSH_TEMPLATE_TRAFFIC_LIMIT_KEY + ":}")
    @Getter
    @Setter
    private String pushTrafficLimit;
}
