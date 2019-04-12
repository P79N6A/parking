package com.zhuyitech.parking.ucc.dto.result.order;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 停车订单余额支付
 *
 * @author walkman
 */
@ApiModel(value = "ParkingOrderBalancePaymentResultDto", description = "停车订单余额支付")
public class ParkingOrderBalancePaymentResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车订单余额支付
     */
    @ApiModelProperty("停车订单余额支付")
    private Boolean payedResult;

    public Boolean getPayedResult() {
        return payedResult;
    }

    public void setPayedResult(Boolean payedResult) {
        this.payedResult = payedResult;
    }
}
