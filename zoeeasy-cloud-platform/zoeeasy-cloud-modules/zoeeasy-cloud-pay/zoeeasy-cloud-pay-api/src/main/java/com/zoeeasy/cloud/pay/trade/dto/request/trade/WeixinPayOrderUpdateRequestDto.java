package com.zoeeasy.cloud.pay.trade.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据支付订单号获取支付记录请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TradePaymentRecordGetRequestDto")
public class WeixinPayOrderUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 交易订单号
     */
    @ApiModelProperty(value = "交易订单号")
    @NotBlank(message = "交易订单号不能为空")
    private String orderNo;
}
