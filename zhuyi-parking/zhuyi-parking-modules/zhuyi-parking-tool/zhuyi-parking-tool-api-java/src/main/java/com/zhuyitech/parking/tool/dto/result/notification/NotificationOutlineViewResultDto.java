package com.zhuyitech.parking.tool.dto.result.notification;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知消息摘要视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NotificationOutlineViewResultDto", description = "通知消息视图模型")
public class NotificationOutlineViewResultDto extends BaseDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 未读通知消息条数
     */
    @ApiModelProperty(value = "未读通知消息条数(多余9条9+)")
    private String unreadAlertCount;

    /**
     * 未读活动消息条数
     */
    @ApiModelProperty(value = "未读活动消息条数(多余9条9+)")
    private String unreadActivityCount;

    /**
     * 未读互动消息条数
     */
    @ApiModelProperty(value = "未读互动消息条数(多余9条9+)")
    private String unreadFeedCount;

    /**
     * 未读快报消息条数
     */
    @ApiModelProperty(value = "未读快报消息条数(多余9条9+)")
    private String unreadNewsCount;

}
