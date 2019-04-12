package com.zhuyitech.parking.tool.dto.request.notification;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 最近两条通知消息请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationLatestGetRequestDto", description = "最近两条通知消息请求参数")
public class NotificationLatestGetRequestDto extends SessionDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
}
