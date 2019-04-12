package com.zhuyitech.parking.tool.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.fastjson.utils.JsonUtils;
import com.zhuyitech.parking.tool.config.JPushConfig;
import com.zhuyitech.parking.tool.domain.Notification;
import com.zhuyitech.parking.tool.domain.NotificationTemplate;
import com.zhuyitech.parking.tool.dto.request.notification.*;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationOutlineViewResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationUnreadCountResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationViewResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.TemplateResultDto;
import com.zhuyitech.parking.tool.enums.NotificationMessageTypeEnum;
import com.zhuyitech.parking.tool.enums.NotificationTypeEnum;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.NotificationCrudService;
import com.zhuyitech.parking.tool.service.NotificationTemplateCrudService;
import com.zhuyitech.parking.tool.service.api.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.text.StrSubstitutor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 消息通知服务
 *
 * @author walkman
 */
@Service("notificationService")
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private static final Integer MAX_NOTIFICATION_COUNT = 9;

    private static final String MAX_NOTIFICATION_STRING = "9+";

    private static final String DEFAULT_NOTIFICATION_MESSAGE_TITLE = "任意停车";

    @Autowired
    private NotificationTemplateCrudService notificationTemplateCrudService;

    @Autowired
    private NotificationCrudService notificationCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JPushConfig jPushConfig;

    @Override
    public ResultDto addTemplate(TemplateAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            NotificationTemplate notificationTemplate = modelMapper.map(requestDto, NotificationTemplate.class);
            if (notificationTemplate != null) {
                notificationTemplateCrudService.insert(notificationTemplate);
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("通知消息模板添加失败" + e.getMessage());
            return resultDto.failed();
        }
    }

    @Override
    public ObjectResultDto<TemplateResultDto> getTemplate(TemplateGetRequestDto requestDto) {
        ObjectResultDto<TemplateResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            NotificationTemplate notificationTemplate = notificationTemplateCrudService.selectById(requestDto.getId());
            if (notificationTemplate != null) {
                TemplateResultDto templateResultDto = modelMapper.map(notificationTemplate, TemplateResultDto.class);
                objectResultDto.setData(templateResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通知消息模板获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ResultDto updateTemplate(TemplateUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            NotificationTemplate smsTemplate = modelMapper.map(requestDto, NotificationTemplate.class);
            if (smsTemplate != null) {
                EntityWrapper<NotificationTemplate> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("Id", requestDto.getId());
                notificationTemplateCrudService.update(smsTemplate, entityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息模板更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto deleteTemplate(TemplateDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<NotificationTemplate> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("Id", requestDto.getId());
            notificationTemplateCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息模板删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public PagedResultDto<TemplateResultDto> queryTemplate(TemplateQueryPagedResultRequestDto dto) {
        PagedResultDto<TemplateResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<NotificationTemplate> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(dto.getTemplateId())) {
                entityWrapper.eq("templateId", dto.getTemplateId());
            }
            if (StringUtils.isNotEmpty(dto.getType())) {
                entityWrapper.eq("type", dto.getType());
            }
            if (dto.getStatus() != null) {
                entityWrapper.eq("status", dto.getStatus());
            }
            Page<NotificationTemplate> templatePage = new Page<>(dto.getPageNo(), dto.getPageSize());
            Page<NotificationTemplate> templateList = notificationTemplateCrudService.selectPage(templatePage, entityWrapper);
            if (templateList != null) {
                List<TemplateResultDto> regionDtoList = modelMapper.map(templateList.getRecords(), new TypeToken<List<TemplateResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(templatePage.getCurrent());
                pagedResultDto.setPageSize(templatePage.getSize());
                pagedResultDto.setTotalCount(templatePage.getTotal());
                pagedResultDto.setItems(regionDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("通知消息模板查询失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 通知消息发送
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addNotification(NotificationSendRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            // 通知模版
            NotificationTemplate notificationTemplate = getNotificationTemplate(requestDto.getTemplateId());
            if (null == notificationTemplate) {
                return resultDto.makeResult(ToolResultEnum.TEMPLATE_NOT_EXIST.getValue(), ToolResultEnum.TEMPLATE_NOT_EXIST.getComment()
                );
            }
            String notificationMessage = "";
            if (StringUtils.isNotEmpty(notificationTemplate.getContent())) {
                // 渲染模板引擎参数，并且校验参数是否完全转换，若无则返回失败
                notificationMessage = StrSubstitutor.replace(notificationTemplate.getContent(), requestDto.getParameters());
                if (notificationMessage.contains("${")) {
                    log.error("消息参数和消息模版中需要替换的参数匹配!");
                    return resultDto.failed();
                }
            }
            Notification notification = new Notification();
            notification.setTemplateId(notificationTemplate.getId());
            notification.setUserId(requestDto.getUserId());
            notification.setNotifyType(requestDto.getNotifyType());
            notification.setBizType(requestDto.getBizType());
            notification.setReadStatus(Boolean.FALSE);
            notification.setTitle(requestDto.getTitle());
            notification.setSendTime(DateUtils.now());
            //根据业务类型设置消息内容
            Map<String, String> extras = new HashMap<String, String>();
            if (requestDto.getData() != null) {
                extras.put("data", requestDto.getData().toJSONString());
            }
            if (StringUtils.isNotEmpty(notificationMessage)) {
                extras.put("content", notificationMessage);
            }
            notification.setContent(JSON.toJSONString(extras));
            notificationCrudService.insert(notification);
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询通知模版
     *
     * @param templateId templateId
     * @return
     */
    private NotificationTemplate getNotificationTemplate(String templateId) {
        // 判断模版编号是否指定
        if (StringUtils.isEmpty(templateId)) {
            log.error(ToolResultEnum.TEMPLATE_UNDEFINED.getComment());
            return null;
        }
        // 查询通知模版
        NotificationTemplate notificationTemplate = notificationTemplateCrudService.getByTemplateId(templateId);
        if (null == notificationTemplate) {
            log.error(ToolResultEnum.TEMPLATE_NOT_EXIST.getComment());
            return null;
        }
        return notificationTemplate;
    }

    /**
     * 通知消息批量添加
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @Override
    public ResultDto batchAddNotification(NotificationBatchAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 未读通知消息
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    @Override
    public ObjectResultDto<NotificationUnreadCountResultDto> unreadCount(NotificationUnreadCountGetRequestDto requestDto) {
        ObjectResultDto<NotificationUnreadCountResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            NotificationUnreadCountResultDto notificationUnreadCountResultDto = new NotificationUnreadCountResultDto();
            Integer unreadCount = notificationCrudService.selectCountByUserIdAndNotifyType(userId, null, Boolean.FALSE);
            if (unreadCount == null || unreadCount.compareTo(0) <= 0) {
                notificationUnreadCountResultDto.setUnreadCount(String.valueOf(0));
            } else {
                notificationUnreadCountResultDto.setUnreadCount(String.valueOf(unreadCount));
            }
            resultDto.setData(notificationUnreadCountResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息获取失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 通知消息摘要
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    @Override
    public ObjectResultDto<NotificationOutlineViewResultDto> outline(NotificationOutlineGetRequestDto requestDto) {
        ObjectResultDto<NotificationOutlineViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            NotificationOutlineViewResultDto notificationOutlineViewResultDto = new NotificationOutlineViewResultDto();

            Integer unreadAlertCount = notificationCrudService.selectCountByUserIdAndNotifyType(userId, NotificationTypeEnum.ALERT.getValue(), Boolean.FALSE);
            notificationOutlineViewResultDto.setUnreadAlertCount(normalizeNotificationCount(unreadAlertCount));

            Integer unreadActivityCount = notificationCrudService.selectCountByUserIdAndNotifyType(userId, NotificationTypeEnum.ACTIVITY.getValue(), Boolean.FALSE);
            notificationOutlineViewResultDto.setUnreadActivityCount(normalizeNotificationCount(unreadActivityCount));

            Integer unreadFeedCount = notificationCrudService.selectCountByUserIdAndNotifyType(userId, NotificationTypeEnum.FEED.getValue(), Boolean.FALSE);
            notificationOutlineViewResultDto.setUnreadFeedCount(normalizeNotificationCount(unreadFeedCount));

            Integer unreadNewsCount = notificationCrudService.selectCountByUserIdAndNotifyType(userId, NotificationTypeEnum.NEW.getValue(), Boolean.FALSE);
            notificationOutlineViewResultDto.setUnreadNewsCount(normalizeNotificationCount(unreadNewsCount));

            resultDto.setData(notificationOutlineViewResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息获取失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 格式化显示消息数目
     *
     * @param count 消息数目
     * @return
     */
    private String normalizeNotificationCount(Integer count) {
        String result = "0";
        if (count == null || count.compareTo(0) <= 0) {
            return result;

        } else {
            if (count.compareTo(MAX_NOTIFICATION_COUNT) > 0) {
                result = MAX_NOTIFICATION_STRING;
            } else {
                result = String.valueOf(count);
            }
        }
        return result;
    }

    /**
     * 最近两条通知消息
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ListResultDto<NotificationViewResultDto> latest(NotificationLatestGetRequestDto requestDto) {
        ListResultDto<NotificationViewResultDto> resultDto = new ListResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            List<Notification> notificationList = notificationCrudService.findLatestTwoListByUserId(userId);
            List<NotificationViewResultDto> notificationViewList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(notificationList)) {
                for (Notification notification : notificationList) {
                    if (notification.getReadStatus()) {
                        continue;
                    }
                    NotificationViewResultDto notificationViewResultDto = new NotificationViewResultDto();
                    notificationViewResultDto.setNotifyId(notification.getId());
                    notificationViewResultDto.setBizType(notification.getBizType());
                    notificationViewResultDto.setNotifyType(notification.getNotifyType());
                    notificationViewResultDto.setTitle(notification.getTitle());
                    notificationViewResultDto.setSendTime(notification.getSendTime());
                    notificationViewResultDto.setReceiveTime(notification.getSendTime());
                    notificationViewResultDto.setRead(notification.getReadStatus());
                    //转换为Map
                    if (StringUtils.isNotEmpty(notification.getContent())) {
                        notificationViewResultDto.setContent(JsonUtils.parseJSON2Map(notification.getContent()));
                    }
                    notificationViewList.add(notificationViewResultDto);
                }
                resultDto.setItems(notificationViewList);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息获取失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取消息列表
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    @Override
    public PagedResultDto<NotificationViewResultDto> notificationList(NotificationListGetRequestDto requestDto) {
        PagedResultDto<NotificationViewResultDto> resultDto = new PagedResultDto<>();
        try {

            Long userId = requestDto.getSessionIdentity().getUserId();
            EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", userId);
            if (requestDto.getNotifyType() != null) {
                entityWrapper.eq("notifyType", requestDto.getNotifyType());
            }
            if (requestDto.getNotifyDate() != null) {
                entityWrapper.ge("sendTime", DateUtils.getStartDate(requestDto.getNotifyDate()));
                entityWrapper.le("sendTime", DateUtils.getEndDate(requestDto.getNotifyDate()));
            }
            entityWrapper.orderBy("sendTime", false);
            Page<Notification> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<Notification> notificationPage = notificationCrudService.selectPage(page, entityWrapper);

            if (notificationPage != null) {
                List<NotificationViewResultDto> notificationViewList = new ArrayList<>();
                if (!notificationPage.getRecords().isEmpty()) {
                    for (Notification notification : notificationPage.getRecords()) {
                        NotificationViewResultDto notificationViewResultDto = new NotificationViewResultDto();
                        notificationViewResultDto.setNotifyId(notification.getId());
                        notificationViewResultDto.setBizType(notification.getBizType());
                        notificationViewResultDto.setNotifyType(notification.getNotifyType());
                        notificationViewResultDto.setTitle(notification.getTitle());
                        notificationViewResultDto.setSendTime(notification.getSendTime());
                        notificationViewResultDto.setReceiveTime(notification.getSendTime());
                        notificationViewResultDto.setRead(notification.getReadStatus());
                        //转换为Map
                        if (StringUtils.isNotEmpty(notification.getContent())) {
                            notificationViewResultDto.setContent(JsonUtils.parseJSON2Map(notification.getContent()));
                        }
                        notificationViewList.add(notificationViewResultDto);
                    }
                    resultDto.setPageNo(notificationPage.getCurrent());
                    resultDto.setPageSize(notificationPage.getSize());
                    resultDto.setTotalCount(notificationPage.getTotal());
                    resultDto.setItems(notificationViewList);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息获取失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 消息已读
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto readNotification(NotificationReadRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Notification notification = new Notification();
            EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getNotifyId());
            notification.setReadStatus(Boolean.TRUE);
            notificationCrudService.update(notification, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息标记已读失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 发送停车新账单通知消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void sendNewParkingOrderNotification(NotificationSendRequestDto requestDto) {
        if (requestDto == null) {
            return;
        }
        try {
            requestDto.setBizType(NotificationMessageTypeEnum.NEW_PARKING_ORDER.getValue());
            requestDto.setNotifyType(NotificationTypeEnum.ALERT.getValue());
            requestDto.setTemplateId(jPushConfig.getMessageNewOrder());
            requestDto.setTitle(DEFAULT_NOTIFICATION_MESSAGE_TITLE);
            addNotification(requestDto);
        } catch (Exception e) {
            log.error("发送停车新账单通知消息失败" + e.getMessage());
        }
    }

    /**
     * 发送停车账单支付成功通知
     *
     * @param requestDto
     */
    @Override
    public void sendParkingOrderPayedNotification(NotificationSendRequestDto requestDto) {
        if (requestDto == null) {
            return;
        }
        try {
            requestDto.setBizType(NotificationMessageTypeEnum.ORDER_PAY_SUCCESS.getValue());
            requestDto.setNotifyType(NotificationTypeEnum.ALERT.getValue());
            requestDto.setTemplateId(jPushConfig.getMessageOrderPayed());
            requestDto.setTitle(DEFAULT_NOTIFICATION_MESSAGE_TITLE);
            addNotification(requestDto);
        } catch (Exception e) {
            log.error("发送停车账单支付成功通知消息失败" + e.getMessage());
        }
    }

    /**
     * 消息通知发送测试
     *
     * @return
     */
    @Override
    public ResultDto notificationSendTest() {
        ResultDto resultDto = new ResultDto();
        try {

            NotificationSendRequestDto notificationSendRequestDto = new NotificationSendRequestDto();
            notificationSendRequestDto.setBizType(NotificationMessageTypeEnum.NEW_PARKING_ORDER.getValue());
            notificationSendRequestDto.setNotifyType(NotificationTypeEnum.ALERT.getValue());
            notificationSendRequestDto.setTemplateId(jPushConfig.getPushNewOrder());
            notificationSendRequestDto.setTitle("任意停车");
            Map<Object, Object> map = new HashedMap();
            map.put("orderDate", "2018年3月22日");
            map.put("parkingName", "逐一科技银湖科技园停车场一号");
            notificationSendRequestDto.setParameters(map);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNo", "12018032913034960000001575373683");
            jsonObject.put("amount", "10.00");
            notificationSendRequestDto.setData(jsonObject);
            notificationSendRequestDto.setUserId(156L);
            addNotification(notificationSendRequestDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息标记已读失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
