package com.zoeeasy.cloud.order.validator.order;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectUpdateParkingOrderRequestDto;
import com.zoeeasy.cloud.order.inspect.validator.InspectUpdateParkingOrderRequestDtoValidator;
import org.springframework.stereotype.Component;

/**
 * 新增地区参数校验
 * Created by song on 2018/9/12.
 */
@Component
public class InspectUpdateParkingOrderRequestDtoValidatorImpl extends ValidatorHandler<InspectUpdateParkingOrderRequestDto> implements InspectUpdateParkingOrderRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, InspectUpdateParkingOrderRequestDto requestDto) {
        if (!(requestDto.getPayWay().equals(PayWayEnum.CASH.getValue()) || requestDto.getPayWay().equals(PayWayEnum.WECHAT_CASH.getValue())
                || requestDto.getPayWay().equals(PayWayEnum.ALIPAY_CASH.getValue()))) {
            throw new ValidationException(OrderConstant.PAYWAY_NOT_CASH);
        }

        return true;
    }
}
