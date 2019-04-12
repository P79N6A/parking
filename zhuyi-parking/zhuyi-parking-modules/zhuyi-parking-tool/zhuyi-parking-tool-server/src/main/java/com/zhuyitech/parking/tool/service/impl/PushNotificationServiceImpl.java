package com.zhuyitech.parking.tool.service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zhuyitech.parking.common.async.AsyncInvokeService;
import com.zhuyitech.parking.common.async.InvokeRunnable;
import com.zhuyitech.parking.common.enums.TerminateType;
import com.zhuyitech.parking.tool.config.JPushConfig;
import com.zhuyitech.parking.tool.domain.NotificationTemplate;
import com.zhuyitech.parking.tool.domain.PushDevice;
import com.zhuyitech.parking.tool.dto.request.push.BatchPushMessageRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushAllMessageRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushMessageTestRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushSingleUserMessageRequestDto;
import com.zhuyitech.parking.tool.enums.DeviceStatus;
import com.zhuyitech.parking.tool.enums.PushMessageType;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.NotificationTemplateCrudService;
import com.zhuyitech.parking.tool.service.PushDeviceCrudService;
import com.zhuyitech.parking.tool.service.api.PushNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息通知推送服务
 *
 * @author walkman
 */
@Service("pushNotificationService")
@Slf4j
public class PushNotificationServiceImpl implements PushNotificationService {

    @Autowired
    private JPushConfig jPushConfig;

    private static final String DEFAULT_NOTIFICATION_MESSAGE_TITLE = "任意停车";

    @Autowired
    private PushDeviceCrudService pushDeviceCrudService;

    @Autowired
    private AsyncInvokeService asyncInvokeService;

    @Autowired
    private NotificationTemplateCrudService notificationTemplateCrudService;

    private static JPushClient jPushClient;

    @Override
    public void pushSingleUser(PushSingleUserMessageRequestDto requestDto) {

        try {
            Long userId = requestDto.getUserId();
            String messageTitle = requestDto.getTitle();
            String messageAlert = requestDto.getTitle();
            String type = requestDto.getMessageType();
            JSONObject data = requestDto.getData();
            // 查询用户的设备
            List<PushDevice> pushDeviceList = pushDeviceCrudService.queryListByUserIdAndStatus(userId, DeviceStatus.VALID.getValue());
            // 判断设备的类型，选择对应的推送方式
            if (pushDeviceList == null || pushDeviceList.isEmpty()) {
                log.debug("用户编号为{}", userId);
                return;
            }
            for (PushDevice pushDevice : pushDeviceList) {
                // IOS推送
                if (TerminateType.APPLE.getValue().equals(pushDevice.getTerminateType())) {

                    Map<String, String> extras = new HashMap<String, String>();
                    extras.put("title", messageTitle);
                    extras.put("type", type);
                    if (data != null) {
                        extras.put("data", data.toJSONString());
                    }
                    if (StringUtils.isNotEmpty(requestDto.getTemplateId())) {
                        String messageContent = buildMessageContent(requestDto.getTemplateId(), requestDto.getParameters());
                        if (StringUtils.isNotEmpty(messageContent)) {
                            messageAlert = messageContent;
                        }
                    }
                    try {
                        PushPayload pushPayload = buildIosPayLoad(messageAlert, extras, Audience.registrationId(pushDevice.getRegistrationId()));
                        PushResult pq = createJPushClient().sendPush(pushPayload);
                        log.debug("IOS推送:" + pq.isResultOK());
                        log.debug(
                                "Push Message[title:{},data:{},messageType:{}] to RegistrationId:{} UserId:{} Success![ISO-SINGLE]",
                                messageTitle, data == null ? "" : data.toJSONString(), type, pushDevice.getRegistrationId(), userId);
                    } catch (Exception e) {
                        log.error(
                                "Push Message[title:{},data:{},messageType:{}] to RegistrationId:{} UserId:{} FAILED![IOS-SINGLE]",
                                messageTitle, data == null ? "" : data.toJSONString(), type, pushDevice.getRegistrationId(), userId);
                        log.error("Push Detail Error：", e);
                    }
                }
                // android推送
                else if (TerminateType.ANDROID.getValue().equals(pushDevice.getTerminateType())) {
                    Map<String, String> extras = new HashMap<>();
                    extras.put("title", messageTitle);
                    extras.put("type", type);
                    if (StringUtils.isNotEmpty(requestDto.getTemplateId())) {
                        String messageContent = buildMessageContent(requestDto.getTemplateId(), requestDto.getParameters());
                        if (StringUtils.isNotEmpty(messageContent)) {
                            messageAlert = messageContent;
                        }
                    }
                    if (data != null) {
                        extras.put("data", data.toJSONString());
                    }
                    try {
                        PushResult pq = createJPushClient().sendAndroidNotificationWithRegistrationID(messageTitle, messageAlert,
                                extras, pushDevice.getRegistrationId());
                        log.debug("ANDROID推送:" + pq.isResultOK());
                        log.debug(
                                "Push Message[title:{},data:{},messageType:{}] to DeviceToken:{} UserId:{} Success![ANDROID-SINGLE]",
                                messageTitle, data == null ? "" : data.toJSONString(), type, pushDevice.getRegistrationId(), userId);
                    } catch (Exception e) {
                        log.error(
                                "Push Message[title:{},data:{},messageType:{}] to DeviceToken:{} UserId:{} FAILED![ANDROID-SINGLE]",
                                messageTitle, data == null ? "" : data.toJSONString(), type, pushDevice.getRegistrationId(), userId);
                        log.error("Push Detail Error：", e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("向个人推送信息出现错误！错误信息：", e);
        }
    }

    @Override
    public void pushAll(PushAllMessageRequestDto requestDto) {
        try {

            String messageTitle = requestDto.getTitle();
            String messageAlert = requestDto.getTitle();
            String messageType = requestDto.getMessageType();
            JSONObject data = requestDto.getData();

            // 给所有android设备发送
            Map<String, String> extras = new HashMap<String, String>();
            extras.put("title", messageTitle);
            extras.put("type", messageType);
            //构建消息内容
            if (StringUtils.isNotEmpty(requestDto.getTemplateId())) {
                String messageContent = buildMessageContent(requestDto.getTemplateId(), requestDto.getParameters());
                if (StringUtils.isNotEmpty(messageContent)) {
                    messageAlert = messageContent;
                }
            }
            if (data != null) {
                extras.put("data", data.toJSONString());
            }
            try {

                PushPayload p = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.all())
                        .setNotification(Notification.android(messageTitle, messageTitle, extras)).build();
                createJPushClient().sendPush(p);
                log.debug("Push Message[title:{},data:{},messageType:{}] Success![ANDROID-ALL]", messageTitle,
                        data == null ? "" : data.toJSONString(), messageType);
            } catch (APIConnectionException | APIRequestException e) {
                log.error("向所有android用户推送信息出现错误！错误信息：", e);
            }

            try {

                PushPayload iosp = buildIosPayLoad(messageAlert, extras, Audience.all());
                createJPushClient().sendPush(iosp);

                log.info("Push Message[title:{},data:{},messageType:{}] Success![IOS-ALL]", messageTitle,
                        data == null ? "" : data.toJSONString(), messageType);
            } catch (APIConnectionException | APIRequestException e) {
                log.error("向所有ios用户推送信息出现错误！错误信息：", e);
            }

        } catch (Exception e) {
            log.error("向所有人推送信息出现错误！错误信息：", e);
        }
    }

    private JPushClient createJPushClient() {
        try {

            if (jPushClient == null) {
                Long live = Long.parseLong(jPushConfig.getTtl());
                if (live == null || live <= 0) {
                    live = 43200L;
                }
                ClientConfig clientConfig = ClientConfig.getInstance();
                clientConfig.setApnsProduction(this.jPushConfig.getApnsproduction());
                clientConfig.setTimeToLive(live);
                // HttpProxy proxy = new HttpProxy("localhost", 3128);
                // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
                //创建全局统一的JPushClient
                jPushClient = new JPushClient(jPushConfig.getMastersecrete(), jPushConfig.getAppkey(), null, clientConfig);
            }
        } catch (Exception e) {
            log.error("创建JPushClient失败", e);
        }
        return jPushClient;
    }

    /**
     * 推送停车入场消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushParkingEnterNotification(PushSingleUserMessageRequestDto requestDto) {
        if (requestDto == null) {
            return;
        }
        requestDto.setTitle(DEFAULT_NOTIFICATION_MESSAGE_TITLE);
        requestDto.setMessageType(PushMessageType.PARKING_ENTER.getCode());
        requestDto.setTemplateId(jPushConfig.getPushParkingEnter());
        pushSingleUser(requestDto);
    }

    /**
     * 推送停车账单产生消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushNewParkingOrderNotification(PushSingleUserMessageRequestDto requestDto) {
        if (requestDto == null) {
            return;
        }
        requestDto.setTitle(DEFAULT_NOTIFICATION_MESSAGE_TITLE);
        requestDto.setMessageType(PushMessageType.NEW_ORDER.getCode());
        requestDto.setTemplateId(jPushConfig.getPushNewOrder());
        pushSingleUser(requestDto);
    }

    /**
     * 推送停车账单产生消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushNewParkingOrderNotification(BatchPushMessageRequestDto requestDto) {
        for (final PushSingleUserMessageRequestDto oneMessage : requestDto.getParams()) {
            oneMessage.setTitle(DEFAULT_NOTIFICATION_MESSAGE_TITLE);
            oneMessage.setMessageType(PushMessageType.NEW_ORDER.getCode());
            oneMessage.setTemplateId(jPushConfig.getPushNewOrder());
            pushSingleUser(oneMessage);
        }
    }

    /**
     * 推送停车账单支付成功消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushParkingOrderPayedNotification(PushSingleUserMessageRequestDto requestDto) {
        requestDto.setMessageType(PushMessageType.ORDER_PAY_SUCCESS.getCode());
        requestDto.setTemplateId(jPushConfig.getPushOrderPayed());
        pushSingleUser(requestDto);
    }

    /**
     * 推送限行提醒消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushTrafficLimitHintNotification(BatchPushMessageRequestDto requestDto) {
        for (final PushSingleUserMessageRequestDto oneMessage : requestDto.getParams()) {
            oneMessage.setTitle("任意停车");
            oneMessage.setMessageType(PushMessageType.TRAFFIC_LIMIT_HINT.getCode());
            oneMessage.setTemplateId(jPushConfig.getPushTrafficLimit());
            asyncInvokeService.submit(new InvokeRunnable() {
                @Override
                public void runSafe() {
                    pushSingleUser(oneMessage);
                }
            });
        }
    }

    /**
     * 构建推送消息内容
     *
     * @param templateId templateId
     * @param parameter  parameter
     * @return 消息文本内容
     */
    private String buildMessageContent(String templateId, Map<Object, Object> parameter) {
        // 判断模版编号是否指定
        if (StringUtils.isEmpty(templateId)) {
            log.error(ToolResultEnum.TEMPLATE_UNDEFINED.getComment());
            return null;
        }
        // 查询推送模版
        NotificationTemplate notificationTemplate = notificationTemplateCrudService.getByTemplateId(templateId);
        if (null == notificationTemplate) {
            log.error(ToolResultEnum.TEMPLATE_NOT_EXIST.getComment());
            return null;
        }
        // 渲染模板引擎参数，并且校验参数是否完全转换，若无则返回失败
        String notificationMessage = StrSubstitutor.replace(notificationTemplate.getContent(), parameter);
        if (notificationMessage.contains("${")) {
            log.error("消息参数和消息模版中需要替换的参数匹配!");
            return null;
        }
        return notificationMessage;
    }

    /**
     * 消息推送测试
     */
    @Override
    public void testPushMessage(PushMessageTestRequestDto requestDto) {

        //发送推送消息
        List<PushSingleUserMessageRequestDto> pushSingleUserList = new ArrayList<>();
        PushSingleUserMessageRequestDto pushSingleUserMessageRequestDto = new PushSingleUserMessageRequestDto();
        pushSingleUserMessageRequestDto.setTemplateId("templateId");
        Map<Object, Object> mapMsg = new HashMap<>();
        mapMsg.put("orderDate", "2018年3月9日");
        mapMsg.put("parkingName", "逐一科技路边停车智慧停车场");
        pushSingleUserMessageRequestDto.setParameters(mapMsg);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", requestDto.getOrderId());
        jsonObject.put("orderNo", requestDto.getOrderNo());
        jsonObject.put("amount", "10.00");
        pushSingleUserMessageRequestDto.setData(jsonObject);
        pushSingleUserMessageRequestDto.setTitle(requestDto.getTitle());
        pushSingleUserMessageRequestDto.setUserId(requestDto.getUserId());
        pushSingleUserMessageRequestDto.setTemplateId(jPushConfig.getMessageNewOrder());
        pushSingleUserList.add(pushSingleUserMessageRequestDto);
        BatchPushMessageRequestDto batchPushMessageRequestDto = new BatchPushMessageRequestDto();
        batchPushMessageRequestDto.setParams(pushSingleUserList);
        pushNewParkingOrderNotification(batchPushMessageRequestDto);
    }

    /**
     * 构建ios消息pushPayLoad
     *
     * @param messageAlert messageTitle
     * @param extras       extras
     * @param audience     audience
     * @return PushPayload
     */
    private PushPayload buildIosPayLoad(String messageAlert, Map extras, Audience audience) {
        //构建payLodBuilder
        Builder payLoadBuilder = PushPayload.newBuilder();
        payLoadBuilder.setPlatform(Platform.ios());
        payLoadBuilder.setAudience(audience);
        //构建iosNotification
//        IosNotification iosNotification = IosNotification.newBuilder().setContentAvailable(true).setAlert(messageAlert).setSound("default").addExtras(extras).build();
        //setBadge(0)去掉角标
        IosNotification iosNotification = IosNotification.newBuilder().setContentAvailable(true).setAlert(messageAlert).setBadge(0).setSound("default").addExtras(extras).build();
        payLoadBuilder.setNotification(Notification.newBuilder().addPlatformNotification(iosNotification).build());
        payLoadBuilder.setOptions(Options.newBuilder().setApnsProduction(true).build());
        return payLoadBuilder.build();
    }
}
