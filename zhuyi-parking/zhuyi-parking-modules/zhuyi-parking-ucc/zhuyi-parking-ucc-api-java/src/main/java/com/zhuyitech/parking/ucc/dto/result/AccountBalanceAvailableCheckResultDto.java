package com.zhuyitech.parking.ucc.dto.result;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车订单余额支付
 *
 * @author zwq
 */
@ApiModel(value = "AccountBalanceAvailableCheckResultDto", description = "停车订单余额支付")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountBalanceAvailableCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车订单余额支付
     */
    @ApiModelProperty("停车订单余额支付")
    private Boolean judgeBalanced;

}
