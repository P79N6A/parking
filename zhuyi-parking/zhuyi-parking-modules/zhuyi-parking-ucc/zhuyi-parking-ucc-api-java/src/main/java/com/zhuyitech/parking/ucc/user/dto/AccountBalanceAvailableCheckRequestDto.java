package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;


/**
 * 判断余额是否足够
 *
 * @author zwq
 * @date 2018-01-11
 */
@ApiModel(value = "AccountBalanceAvailableCheckRequestDto", description = "余额支付")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountBalanceAvailableCheckRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", required = true, notes = "金额(0-10000.00)")
    @Digits(message = "支付金额无效", integer = 100000, fraction = 2)
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    private BigDecimal paymentAmount;

}
