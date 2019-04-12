package com.zoeeasy.cloud.notify.notification.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 通知消息删除请求参数
 *
 * @date: 2018/11/16.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NotificationDeleteRequestDto", description = "通知消息删除请求参数")
public class NotificationDeleteRequestDto extends SignedSessionRequestDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 通知消息ID
     */
    @ApiModelProperty(value = "通知消息ID")
    @NotNull(message = NotifyConstant.NOTIFY_ID_NOT_NULL)
    private List<Long> ids;

}
