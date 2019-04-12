package com.zoeeasy.cloud.notify.push.dto.request;

import cn.jpush.api.push.model.audience.Audience;
import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author AkeemSuper
 * @date 2018/11/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PushBatchByTagRequestDto", description = "通过tag批量推送请求参数")
public class PushBatchByTagRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 推送方
     */
    @ApiModelProperty(value = "推送方")
    private Audience audience;

    /**
     * 模板ID
     */
    @ApiModelProperty(value = "模板ID")
    private String templateId;

    /**
     *
     */
    @ApiModelProperty(value = "消息类型")
    private String messageType;

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
