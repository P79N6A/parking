package com.zoeeasy.cloud.notify.notification;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.notify.notification.dto.request.*;
import com.zoeeasy.cloud.notify.notification.dto.result.NotificationViewResultDto;
import com.zoeeasy.cloud.notify.notification.dto.result.TemplateResultDto;

/**
 * 消息通知服务
 *
 * @author walkamn
 */
public interface NotificationService {

    /**
     * 添加消息通知模板
     *
     * @param requestDto TemplateAddRequestDto
     * @return ResultDto
     */
    ResultDto addTemplate(TemplateAddRequestDto requestDto);

    /**
     * 修改消息通知模板
     *
     * @param requestDto TemplateUpdateRequestDto
     * @return ResultDto
     */
    ResultDto updateTemplate(TemplateUpdateRequestDto requestDto);

    /**
     * 删除消息通知模板
     *
     * @param requestDto TemplateDeleteRequestDto
     * @return ResultDto
     */
    ResultDto deleteTemplate(TemplateDeleteRequestDto requestDto);

    /**
     * 查询消息通知模板
     *
     * @param requestDto TemplateGetRequestDto
     * @return ObjectResultDto<TemplateResultDto>
     */
    ObjectResultDto<TemplateResultDto> getTemplate(TemplateGetRequestDto requestDto);

    /**
     * 分页查询消息通知模板
     *
     * @param requestDto TemplateQueryPagedResultRequestDto
     * @return PagedResultDto<TemplateResultDto>
     */
    PagedResultDto<TemplateResultDto> queryTemplate(TemplateQueryPagedResultRequestDto requestDto);

    /**
     * 通知消息添加
     *
     * @param requestDto NotificationSendRequestDto
     * @return ResultDto
     */
    ResultDto addNotification(NotificationSendRequestDto requestDto);

    /**
     * 查询消息通知模板
     *
     * @param requestDto TemplateQueryRequestDto
     * @return TemplateResultDto
     */
    TemplateResultDto getNotificationTemplateByType(TemplateQueryRequestDto requestDto);

    /**
     * 分页获取消息列表
     *
     * @param requestDto NotificationListGetRequestDto
     * @return PagedResultDto<NotificationViewResultDto>
     */
    PagedResultDto<NotificationViewResultDto> notificationList(NotificationListGetRequestDto requestDto);

    /**
     * 消息已读
     *
     * @param requestDto NotificationReadRequestDto
     * @return ResultDto
     */
    ResultDto readNotification(NotificationReadRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 批量删除消息
     *
     * @param requestDto NotificationDeleteRequestDto
     * @return ResultDto
     */
    ResultDto deleteNotification(NotificationDeleteRequestDto requestDto);
}
