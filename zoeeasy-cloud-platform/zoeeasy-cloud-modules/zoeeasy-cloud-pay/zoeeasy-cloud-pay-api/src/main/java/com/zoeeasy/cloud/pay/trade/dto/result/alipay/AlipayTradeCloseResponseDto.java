package com.zoeeasy.cloud.pay.trade.dto.result.alipay;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝查询订单响应参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayTradeQueryResponseDto", description = "支付宝查询订单响应参数")
public class AlipayTradeCloseResponseDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 网关返回码
     */
    @ApiModelProperty(value = "code", required = true)
    private String code;

    /**
     * 网关返回码描述
     */
    @ApiModelProperty(value = "msg", required = true)
    private String msg;

    /**
     * 业务返回码
     */
    @ApiModelProperty(value = "subCode")
    private String subCode;

    /**
     * 业务返回码描述
     */
    @ApiModelProperty(value = "subMsg")
    private String subMsg;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty(value = "tradeNo", required = true)
    private String tradeNo;

    /**
     * 商家订单号
     */
    @ApiModelProperty(value = "outTradeNo", required = true)
    private String outTradeNo;
}
