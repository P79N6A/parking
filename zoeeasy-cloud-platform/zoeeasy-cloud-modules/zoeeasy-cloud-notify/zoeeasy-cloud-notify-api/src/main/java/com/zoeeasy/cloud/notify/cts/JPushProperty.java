package com.zoeeasy.cloud.notify.cts;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/11/7 0007
 */
@Component("jpushProperty")
@Data
@EqualsAndHashCode(callSuper = false)
public class JPushProperty {

    /**
     * 读取配置文件中的masterSecret
     */
    @Value("${" + JPushPropertyKeys.JPUSH_MASTER_SECRETE + ":45603a23d94373b9fc71c904}")
    public String masterSecret;

    /**
     * 读取配置文件中的appKey
     */
    @Value("${" + JPushPropertyKeys.JPUSH_APPKEY + ":0fbb9a2ab3d4b9a4ef4e3b87}")
    private String appkey;

    /**
     * 读取配置文件中的apnsFlg
     */
    @Value("${" + JPushPropertyKeys.JPUSH_APNSPRODUCTION + ":false}")
    private Boolean apnsPord;

    /**
     * ttl
     */
    @Value("${" + JPushPropertyKeys.JPUSH_TTL + ":43200}")
    private Long ttl;

    /**
     * 停车新账单消息模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_MESSAGE_NEW_ORDER + ":NOT_20180524}")
    private String messageNewOrder;

    /**
     * 停车账单支付成功消息模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_MESSAGE_ORDER_PAYED + ":NOT_20180526}")
    private String messageOrderPayed;

    /**
     * 停车账单支付成功消息模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_TEMPLATE_ORDER_PAYED + ":PUSH_20180526}")
    private String pushOrderPayed;

    /**
     * 停车新账单消息模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_TEMPLATE_NEW_ORDER + ":PUSH_20180524}")
    private String pushNewOrder;

    /**
     * 限行提醒消息模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_TEMPLATE_TRAFFIC_LIMIT + ":PUSH_20180601}")
    private String pushTrafficLimit;

    /**
     * 停车入场推送消息模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_TEMPLATE_PARKING_ENTER + ":PUSH_20180628}")
    private String pushParkingEnter;

    /**
     * 入车车巡检推送模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_TEMPLATE_PASS_RECORD_INTO_INSPECT + ":PUSH_20181105}")
    private String pushPassRecordIntoInspect;

    /**
     * 出车推送巡检模板
     */
    @Value("${" + JPushPropertyKeys.JPUSH_TEMPLATE_PASS_RECORD_OUT_INSPECT + ":PUSH_20181107}")
    private String pushPassRecordOutInspect;
    /**
     * 入车车巡检消息模板
     */
    @Value("${" + JPushPropertyKeys.MESSAGE_TEMPLATE_PASS_RECORD_INTO_INSPECT + ":MESSAGE_PASS_INTO}")
    private String messagePassRecordIntoInspect;

    /**
     * 出车推送消息模板
     */
    @Value("${" + JPushPropertyKeys.MESSAGEH_TEMPLATE_PASS_RECORD_OUT_INSPECT + ":MESSAGE_PASS_OUT}")
    private String messagePassRecordOutInspect;


}
