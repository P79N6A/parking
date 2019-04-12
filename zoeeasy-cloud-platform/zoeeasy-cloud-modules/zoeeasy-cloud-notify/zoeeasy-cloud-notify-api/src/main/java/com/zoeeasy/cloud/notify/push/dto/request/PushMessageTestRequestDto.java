package com.zoeeasy.cloud.notify.push.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息推送测试请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PushMessageTestRequestDto", description = "消息推送测试请求参数")
public class PushMessageTestRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "")
    private Long orderId;

    @ApiModelProperty(value = "title")
    private String title;

    /**
     *
     */
    @ApiModelProperty(value = "orderNo")
    private String orderNo;

}
