package com.zhuyitech.parking.tool.dto.request.notification;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 未读通知消息数目请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationUnreadCountGetRequestDto", description = "未读通知消息数目请求参数")
public class NotificationUnreadCountGetRequestDto extends SessionDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
}
