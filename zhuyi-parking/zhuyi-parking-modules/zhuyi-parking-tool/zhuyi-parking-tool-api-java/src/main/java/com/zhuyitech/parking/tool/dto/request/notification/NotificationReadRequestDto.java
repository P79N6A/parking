package com.zhuyitech.parking.tool.dto.request.notification;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationReadRequestDto", description = "通知消息已读请求参数")
public class NotificationReadRequestDto extends SessionDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 通知消息ID
     */
    @ApiModelProperty(value = "通知消息ID")
    @NotNull(message = "通知消息ID不能为空")
    private Long notifyId;

}
