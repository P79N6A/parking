package com.zoeeasy.cloud.inspect.controller.inspect;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.inspect.InspectPushService;
import com.zoeeasy.cloud.integration.push.dto.request.PushTagGetInfoRequestDto;
import com.zoeeasy.cloud.integration.push.dto.result.PushTagGetInfoResultDto;
import com.zoeeasy.cloud.notify.notification.NotificationService;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationDeleteRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationListGetRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationReadRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.result.NotificationViewResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2018/11/16.
 * @author：zm
 */
@Api(tags = "消息API", value = "消息API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@ApiVersion(1)
@ApiSigned
@RequestMapping("/cloud/inspect")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private InspectPushService inspectPushService;

    /**
     * 分页获取消息列表
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    @ApiOperation(value = "分页获取消息列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = NotificationViewResultDto.class)
    @PostMapping(value = "/notifyPageList")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inspect_parking_id", required = true, paramType = "header",
                            dataType = "string", value = "parkingId header", defaultValue = "inspect_parking_id:")
            }
    )
    public PagedResultDto<NotificationViewResultDto> notificationList(@RequestBody NotificationListGetRequestDto requestDto) {
        return notificationService.notificationList(requestDto);
    }

    /**
     * 消息已读
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @ApiOperation(value = "消息已读", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/readNotify")
    public ResultDto readNotification(@RequestBody NotificationReadRequestDto requestDto) {
        return notificationService.readNotification(requestDto);
    }

    /**
     * 消息删除
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @ApiOperation(value = "消息删除", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/deleteNotify")
    public ResultDto deleteNotification(@RequestBody NotificationDeleteRequestDto requestDto) {
        return notificationService.deleteNotification(requestDto);
    }

    /**
     * 获取用户推送标签
     *
     * @param requestDto PushTagGetInfoRequestDto
     * @return 获取用户推送标签
     */
    @ApiOperation(value = "获取用户推送标签", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = PushTagGetInfoResultDto.class)
    @PostMapping(value = "/getPushTag")
    public ObjectResultDto<PushTagGetInfoResultDto> getPushTag(@RequestBody PushTagGetInfoRequestDto requestDto) {
        return inspectPushService.getPushTag(requestDto);
    }

}
