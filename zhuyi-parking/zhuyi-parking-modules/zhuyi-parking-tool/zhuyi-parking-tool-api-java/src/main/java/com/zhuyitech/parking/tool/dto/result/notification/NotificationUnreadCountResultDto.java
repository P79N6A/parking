package com.zhuyitech.parking.tool.dto.result.notification;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 未读通知消息数目视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationUnreadCountResultDto", description = "未读通知消息数目视图模型")
public class NotificationUnreadCountResultDto extends BaseDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 未读通知消息条数
     */
    @ApiModelProperty(value = "未读通知消息条数(多余9条9+)")
    private String unreadCount;

}
