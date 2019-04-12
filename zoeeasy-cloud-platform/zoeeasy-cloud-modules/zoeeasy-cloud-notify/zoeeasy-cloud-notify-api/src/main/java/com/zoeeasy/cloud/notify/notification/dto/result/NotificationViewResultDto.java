package com.zoeeasy.cloud.notify.notification.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.convert.serializer.ToShortDateTimeJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;

/**
 * 通知消息视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NotificationViewResultDto", description = "通知消息视图模型")
public class NotificationViewResultDto extends BaseDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 通知消息ID
     */
    @ApiModelProperty(value = "通知消息ID")
    private Long notifyId;

    /**
     * 消息通知类型(1:通知 2:活动 3:互动 4:快报)
     */
    @ApiModelProperty(value = "消息通知类型(1:通知 2:活动 3:互动 4:快报)")
    private Integer notifyType;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private Integer bizType;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private Map<String, Object> content;

    /**
     * 是否已读
     */
    @ApiModelProperty(value = "是否已读")
    private Boolean read;

    /**
     * 消息接收时间
     */
    @ApiModelProperty(value = "消息接收时间")
    @JsonSerialize(using = ToShortDateTimeJsonSerializer.class)
    private Date receiveTime;

    /**
     * 消息发送时间
     */
    @ApiModelProperty(value = "消息发送时间")
    private Date sendTime;

}
