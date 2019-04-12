package com.zhuyitech.parking.tool.dto.request.notification;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 批量通知消息添加请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationBatchAddRequestDto", description = "批量通知消息添加请求参数")
public class NotificationBatchAddRequestDto extends BaseDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private List<NotificationSendRequestDto> params;

}
