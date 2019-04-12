package com.zoeeasy.cloud.pay.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.pay.trade.dto.request.order.RechargeSuccessRequestDto;
import com.zoeeasy.cloud.pay.trade.validator.RechargeSuccessRequestDtoValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 充值成功参数验证
 * zwq
 */
@Component
public class RechargeSuccessRequestDtoValidatorImpl extends ValidatorHandler<RechargeSuccessRequestDto> implements RechargeSuccessRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, RechargeSuccessRequestDto requestDto) {
        PayWayEnum payWay = PayWayEnum.valueOf(requestDto.getPayWay());
        if (payWay == null) {
            throw new RuntimeException("支付方式为空");
        }
        if (requestDto.getOrderAmount() == null || requestDto.getOrderAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("订单金额有误");
        }
        if (requestDto.getTotalAmount() == null || requestDto.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("订单金额有误");
        }
        return true;
    }
}
