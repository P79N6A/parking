package com.zoeeasy.cloud.pay.trade.dto.request.weixin;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取微信必要参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeixinConfigGetRequestDto", description = "获取微信必要参数")
public class WeixinConfigGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;

}
