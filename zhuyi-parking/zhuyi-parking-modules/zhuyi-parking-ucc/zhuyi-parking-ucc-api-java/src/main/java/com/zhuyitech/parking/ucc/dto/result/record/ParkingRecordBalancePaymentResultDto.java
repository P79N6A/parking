package com.zhuyitech.parking.ucc.dto.result.record;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 停车订单余额支付
 *
 * @author zwq
 */
@ApiModel(value = "ParkingRecordBalancePaymentResultDto", description = "停车订单余额支付")
public class ParkingRecordBalancePaymentResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车订单余额支付
     */
    @ApiModelProperty("停车订单余额支付")
    private Boolean payForBalanced;

    public Boolean getPayForBalanced() {
        return payForBalanced;
    }

    public void setPayForBalanced(Boolean payForBalanced) {
        this.payForBalanced = payForBalanced;
    }
}
