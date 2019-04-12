package com.zoeeasy.cloud.collect.msgbody.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019-03-03
 * @author: wf
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentNotifyResultBody", description = "支付结果通知返回参数")
public class PaymentNotifyResultBody extends BaseResultBody {
    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场Code
     */
    @ApiModelProperty("本地停车场Code")
    private String localCode;

    /**
     * 本地停车场订单流水号
     */
    @ApiModelProperty("本地停车场订单流水号")
    private String parkingOrderNo;
}
