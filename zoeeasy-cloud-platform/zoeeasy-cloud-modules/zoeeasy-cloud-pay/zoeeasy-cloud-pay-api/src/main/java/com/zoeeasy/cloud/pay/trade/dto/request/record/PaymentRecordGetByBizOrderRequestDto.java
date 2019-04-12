package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
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
@ApiModel(value = "PaymentRecordGetByBizOrderRequestDto", description = "根据订单查询")
public class PaymentRecordGetByBizOrderRequestDto extends EntityDto<Long> {

    public static final long serialVersionUID = 1L;

    /**
     * 交易业务订单号
     */
    @ApiModelProperty("交易业务订单号")
    private String bizOrderNo;

    /**
     * 交易业务类型
     */
    @ApiModelProperty("交易业务类型")
    private Integer bizOrderType;
}
