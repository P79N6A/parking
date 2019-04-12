package com.zhuyitech.parking.tool.dto.request.push;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@EqualsAndHashCode(callSuper = true)
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
