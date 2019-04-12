package com.zoeeasy.cloud.notify.notification.dto.request;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 通知消息发送请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NotificationSendRequestDto", description = "通知消息发送请求参数")
public class NotificationSendRequestDto extends BaseDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    /**
     * 模板ID
     */
    @ApiModelProperty(value = "模板ID")
    private String templateId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long userId;

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
     * 消息参数(替换模板中的参数)
     */
    @ApiModelProperty(value = "parameters", notes = "消息参数")
    private Map<Object, Object> parameters;

    /**
     * JSON形式的消息内容
     */
    @ApiModelProperty(value = "JSON形式的消息内容")
    private JSONObject data = new JSONObject();

}
