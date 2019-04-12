package com.zoeeasy.cloud.notify.service.impl;

import com.alibaba.fastjson.JSON;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.session.SessionParkingInfo;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.fastjson.utils.JsonUtils;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import com.zoeeasy.cloud.notify.domain.Notification;
import com.zoeeasy.cloud.notify.domain.NotificationTemplate;
import com.zoeeasy.cloud.notify.enums.NotifyResultEnum;
import com.zoeeasy.cloud.notify.notification.NotificationService;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationDeleteRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationListGetRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationReadRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationSendRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.TemplateAddRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.TemplateDeleteRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.TemplateGetRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.TemplateQueryPagedResultRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.TemplateQueryRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.TemplateUpdateRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.result.NotificationViewResultDto;
import com.zoeeasy.cloud.notify.notification.dto.result.TemplateResultDto;
import com.zoeeasy.cloud.notify.notification.validator.NotificationReadRequestDtoValidator;
import com.zoeeasy.cloud.notify.service.NotificationCrudService;
import com.zoeeasy.cloud.notify.service.NotificationTemplateCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private NotificationTemplateCrudService notificationTemplateCrudService;

    @Autowired
    private NotificationCrudService notificationCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加消息通知模板
     *
     * @param requestDto TemplateAddRequestDto
     * @return ResultDto
     */
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

    /**
     * 查询消息通知模板
     *
     * @param requestDto TemplateGetRequestDto
     * @return ObjectResultDto<TemplateResultDto>
     */
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

    /**
     * 修改消息通知模板
     *
     * @param requestDto TemplateUpdateRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateTemplate(TemplateUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            NotificationTemplate smsTemplate = modelMapper.map(requestDto, NotificationTemplate.class);
            if (smsTemplate != null) {
                EntityWrapper<NotificationTemplate> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", requestDto.getId());
                notificationTemplateCrudService.update(smsTemplate, entityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息模板更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除消息通知模板
     *
     * @param requestDto TemplateDeleteRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto deleteTemplate(TemplateDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<NotificationTemplate> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            notificationTemplateCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("通知消息模板删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页查询消息通知模板
     *
     * @param dto TemplateQueryPagedResultRequestDto
     * @return PagedResultDto<TemplateResultDto>
     */
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
     * @param requestDto NotificationSendRequestDto
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
                return resultDto.makeResult(NotifyResultEnum.TEMPLATE_NOT_EXIST.getValue(), NotifyResultEnum.TEMPLATE_NOT_EXIST.getComment()
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
            notification.setTenantId(requestDto.getTenantId());
            //根据业务类型设置消息内容
            Map<String, String> extras = new HashMap<>();
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
     * @param requestDto TemplateQueryRequestDto
     * @return NotificationTemplate
     */
    @Override
    public TemplateResultDto getNotificationTemplateByType(TemplateQueryRequestDto requestDto) {
        //根据租户id去模板表里查
        EntityWrapper<NotificationTemplate> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("tenantId", requestDto.getTenantId());
        entityWrapper.eq("type", requestDto.getType());
        NotificationTemplate notificationTemplate = notificationTemplateCrudService.findTemplateId(entityWrapper);
        if (notificationTemplate != null) {
            return modelMapper.map(notificationTemplate, TemplateResultDto.class);
        } else {
            return null;
        }
    }

    /**
     * 分页获取消息列表
     *
     * @param requestDto NotificationListGetRequestDto
     * @return PagedResultDto<NotificationViewResultDto>
     */
    @Override
    public PagedResultDto<NotificationViewResultDto> notificationList(NotificationListGetRequestDto requestDto) {
        PagedResultDto<NotificationViewResultDto> resultDto = new PagedResultDto<>();
        try {
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (currentParking == null) {
                return resultDto.makeResult(NotifyResultEnum.PARKING_ID_NOT_EMPTY.getValue(), NotifyResultEnum.PARKING_ID_NOT_EMPTY.getComment());
            }
            Long parkingId = currentParking.getParkingId();
            Long userId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", userId);
            if (requestDto.getNotifyType() != null) {
                entityWrapper.eq("notifyType", requestDto.getNotifyType());
            }
            if (requestDto.getReadStatus() != null) {
                entityWrapper.eq("readStatus", requestDto.getReadStatus());
            }
            if (requestDto.getNotifyDate() != null) {
                entityWrapper.ge(NotifyConstant.SEND_TIME, DateUtils.getStartDate(requestDto.getNotifyDate()));
                entityWrapper.le(NotifyConstant.SEND_TIME, DateUtils.getEndDate(requestDto.getNotifyDate()));
            } else {
                entityWrapper.ge(NotifyConstant.SEND_TIME, DateUtils.getStartDate(DateUtils.now()));
                entityWrapper.le(NotifyConstant.SEND_TIME, DateUtils.getEndDate(DateUtils.now()));
            }
            entityWrapper.like("content", NotifyConstant.PARKING_ID + parkingId);
            entityWrapper.orderBy(NotifyConstant.SEND_TIME, false);
            Page<Notification> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<Notification> notificationPage = notificationCrudService.selectPage(page, entityWrapper);
            List<Notification> records = notificationPage.getRecords();
            if (CollectionUtils.isNotEmpty(records)) {
                List<NotificationViewResultDto> notificationViewList = new ArrayList<>();
                for (Notification notification : records) {
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
                resultDto.setPageNo(requestDto.getPageNo());
                resultDto.setPageSize(requestDto.getPageSize());
                resultDto.setTotalCount(notificationPage.getTotal());
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
     * 消息已读
     *
     * @param requestDto NotificationReadRequestDto
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto readNotification(@FluentValid(NotificationReadRequestDtoValidator.class) NotificationReadRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getNotifyId());
            entityWrapper.eq("userId", userId);
            Notification notification = new Notification();
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
     * 批量删除消息
     *
     * @param requestDto NotificationDeleteRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto deleteNotification(NotificationDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            if (CollectionUtils.isNotEmpty(requestDto.getIds())) {
                notificationCrudService.deleteNotification(requestDto.getIds(), userId);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("批量删除消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询通知模版
     *
     * @param templateId templateId
     * @return NotificationTemplate
     */
    private NotificationTemplate getNotificationTemplate(String templateId) {
        // 判断模版编号是否指定
        if (StringUtils.isEmpty(templateId)) {
            log.error(NotifyResultEnum.TEMPLATE_UNDEFINED.getComment());
            return null;
        }
        // 查询通知模版
        NotificationTemplate notificationTemplate = notificationTemplateCrudService.getByTemplateId(templateId);
        if (null == notificationTemplate) {
            log.error(NotifyResultEnum.TEMPLATE_NOT_EXIST.getComment());
            return null;
        }
        return notificationTemplate;
    }

}
