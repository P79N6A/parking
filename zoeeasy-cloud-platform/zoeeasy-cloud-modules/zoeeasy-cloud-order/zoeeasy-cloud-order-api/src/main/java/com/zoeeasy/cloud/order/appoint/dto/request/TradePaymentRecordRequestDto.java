package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * MQ处理支付记录
 *
 * @author zwq
 * @date 2018-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TradePaymentRecordRequestDto", description = "MQ处理支付记录")
public class TradePaymentRecordRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @ApiModelProperty("订单流水号")
    @NotBlank(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;
}
