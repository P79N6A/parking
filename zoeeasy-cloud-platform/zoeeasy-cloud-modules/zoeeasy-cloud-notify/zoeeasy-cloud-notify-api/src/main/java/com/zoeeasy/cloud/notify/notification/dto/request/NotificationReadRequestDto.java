package com.zoeeasy.cloud.notify.notification.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 通知消息已读请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NotificationReadRequestDto", description = "通知消息已读请求参数")
public class NotificationReadRequestDto extends SignedSessionRequestDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 通知消息ID
     */
    @ApiModelProperty(value = "通知消息ID")
    @NotNull(message = NotifyConstant.NOTIFY_ID_NOT_NULL)
    private Long notifyId;

}
