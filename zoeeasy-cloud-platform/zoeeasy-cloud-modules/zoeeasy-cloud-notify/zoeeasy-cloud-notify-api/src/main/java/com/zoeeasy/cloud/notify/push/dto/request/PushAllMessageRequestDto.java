package com.zoeeasy.cloud.notify.push.dto.request;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 所有用户消息推送请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PushAllMessageRequestDto", description = "所有用户消息推送请求参数")
public class PushAllMessageRequestDto extends BaseDto {

    /**
     * @field serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型")
    private String messageType;

    /**
     * 模板ID
     */
    @ApiModelProperty(value = "模板ID")
    private String templateId;

    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题")
    private String title;

    /**
     * 消息内容参数
     */
    @ApiModelProperty(value = "消息内容参数")
    private Map<Object, Object> parameters;

    /**
     * JSON形式的消息内容
     */
    @ApiModelProperty(value = "JSON形式的消息内容")
    private JSONObject data = new JSONObject();

}
