package com.zhuyitech.parking.integration.order.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车账单支付判断
 *
 * @author zwq
 * @date 2018-08-03
 */
@ApiModel(value = "PaymentCheckResultDto", description = "停车账单支付判断")
@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否可支付
     */
    @ApiModelProperty("是否可支付")
    private Boolean payCheck;

}
