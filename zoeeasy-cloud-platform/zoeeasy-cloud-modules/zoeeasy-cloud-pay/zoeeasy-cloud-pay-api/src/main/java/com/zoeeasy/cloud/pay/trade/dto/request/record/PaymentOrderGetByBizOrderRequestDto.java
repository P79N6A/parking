package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据订单查询
 *
 * @Date: 2018/06/27
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentOrderGetByBizOrderRequestDto", description = "根据订单查询")
public class PaymentOrderGetByBizOrderRequestDto extends SessionEntityDto<Long> {

    public static final long serialVersionUID = 1L;

    /**
     * 交易业务订单号
     */
    @ApiModelProperty("交易业务订单号")
    private String bizOrderNo;
}
