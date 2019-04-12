package com.zoeeasy.cloud.collect.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2019/1/15
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentNotifyResultDto", description = "支付结果通知返回参数")
public class PaymentNotifyResultDto extends BaseDto {

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
