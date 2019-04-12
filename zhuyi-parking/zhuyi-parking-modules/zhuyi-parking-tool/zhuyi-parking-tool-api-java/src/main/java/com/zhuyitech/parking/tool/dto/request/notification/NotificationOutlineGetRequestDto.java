package com.zhuyitech.parking.tool.dto.request.notification;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 通知消息摘要请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationOutlineGetRequestDto", description = "通知消息摘要请求参数")
public class NotificationOutlineGetRequestDto extends SessionDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
}
