package com.zoeeasy.cloud.notify.push.dto.request;

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
@ApiModel(value = "PushPassInspectNotifyRequestDto", description = "过车推送巡检请求参数")
public class PushPassInspectNotifyRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    private Long tenantId;

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

    /**
     * 推送标签组
     */
    @ApiModelProperty(value = "推送标签组")
    private Map<Integer, String> tags;
}
