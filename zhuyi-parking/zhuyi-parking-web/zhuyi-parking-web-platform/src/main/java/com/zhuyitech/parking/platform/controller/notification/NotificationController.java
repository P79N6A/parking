package com.zhuyitech.parking.platform.controller.notification;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.notification.*;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationOutlineViewResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationUnreadCountResultDto;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationViewResultDto;
import com.zhuyitech.parking.tool.service.api.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通知消息API
 *
 * @author walkmans
 * @date 2018/1/13
 */
@Api(value = "通知消息API", description = "通知消息API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 最近两条通知消息
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "通知消息摘要", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = NotificationViewResultDto.class)
    @RequestMapping(value = "/latest", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ListResultDto<NotificationViewResultDto> latest(NotificationLatestGetRequestDto requestDto) {
        return notificationService.latest(requestDto);
    }

    /**
     * 未读通知消息数目
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    @ApiOperation(value = "未读通知消息数目", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = NotificationUnreadCountResultDto.class)
    @RequestMapping(value = "/unreadCount", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<NotificationUnreadCountResultDto> unreadCount(NotificationUnreadCountGetRequestDto requestDto) {
        return notificationService.unreadCount(requestDto);
    }

    /**
     * 通知消息摘要
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    @ApiOperation(value = "通知消息摘要", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = NotificationOutlineViewResultDto.class)
    @RequestMapping(value = "/outline", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<NotificationOutlineViewResultDto> outline(NotificationOutlineGetRequestDto requestDto) {
        return notificationService.outline(requestDto);
    }

    /**
     * 分页获取消息列表
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    @ApiOperation(value = "分页获取消息列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = NotificationViewResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public PagedResultDto<NotificationViewResultDto> notificationList(NotificationListGetRequestDto requestDto) {
        return notificationService.notificationList(requestDto);
    }

    /**
     * 消息已读
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @ApiOperation(value = "消息已读", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto readNotification(NotificationReadRequestDto requestDto) {
        return notificationService.readNotification(requestDto);
    }
}
