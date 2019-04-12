package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.notification.*;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationOutlineViewResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationUnreadCountResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationViewResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.TemplateResultDto;

/**
 * 消息通知服务
 *
 * @author walkamn
 */
public interface NotificationService {

    /**
     * 添加消息通知模板
     *
     * @param requestDto
     * @return
     */
    ResultDto addTemplate(TemplateAddRequestDto requestDto);

    /**
     * 修改消息通知模板
     *
     * @param requestDto
     * @return
     */
    ResultDto updateTemplate(TemplateUpdateRequestDto requestDto);

    /**
     * 删除消息通知模板
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteTemplate(TemplateDeleteRequestDto requestDto);

    /**
     * 查询消息通知模板
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<TemplateResultDto> getTemplate(TemplateGetRequestDto requestDto);

    /**
     * 分页查询消息通知模板
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<TemplateResultDto> queryTemplate(TemplateQueryPagedResultRequestDto requestDto);

    /**
     * 通知消息添加
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto addNotification(NotificationSendRequestDto requestDto);

    /**
     * 通知消息批量添加
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto batchAddNotification(NotificationBatchAddRequestDto requestDto);

    /**
     * 未读通知消息
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    ObjectResultDto<NotificationUnreadCountResultDto> unreadCount(NotificationUnreadCountGetRequestDto requestDto);

    /**
     * 通知消息摘要
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    ObjectResultDto<NotificationOutlineViewResultDto> outline(NotificationOutlineGetRequestDto requestDto);

    /**
     * 最近两条通知消息
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<NotificationViewResultDto> latest(NotificationLatestGetRequestDto requestDto);

    /**
     * 分页获取消息列表
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    PagedResultDto<NotificationViewResultDto> notificationList(NotificationListGetRequestDto requestDto);

    /**
     * 消息已读
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto readNotification(NotificationReadRequestDto requestDto);

    /**
     * 发送停车新账单通知消息
     *
     * @param requestDto requestDto
     */
    void sendNewParkingOrderNotification(NotificationSendRequestDto requestDto);

    /**
     * 发送停车账单支付成功通知
     *
     * @param requestDto requestDto
     */
    void sendParkingOrderPayedNotification(NotificationSendRequestDto requestDto);

    /**
     * 消息通知发送测试
     *
     * @return
     */
    ResultDto notificationSendTest();

}
