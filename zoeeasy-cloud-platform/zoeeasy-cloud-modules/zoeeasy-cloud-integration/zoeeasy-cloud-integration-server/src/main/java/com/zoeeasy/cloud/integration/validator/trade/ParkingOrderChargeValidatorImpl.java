package com.zoeeasy.cloud.integration.validator.trade;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.integration.trade.dto.ParkingOrderChargeRequestDto;
import com.zoeeasy.cloud.integration.trade.validator.ParkingOrderChargeValidator;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import org.springframework.stereotype.Component;


/**
 * 收款业务校验
 *
 * @author walkman
 * @date 2019-01-16
 */
@Component
public class ParkingOrderChargeValidatorImpl extends ValidatorHandler<ParkingOrderChargeRequestDto> implements ParkingOrderChargeValidator {

    @Override
    public boolean validate(ValidatorContext context, ParkingOrderChargeRequestDto requestDto) {

        //支付方式校验, 只支持支付宝、微信二维码收款
        PayWayEnum payWayEnum = PayWayEnum.valueOf(requestDto.getPayWay());
        if (payWayEnum == null
                || !(PayWayEnum.ALIPAY.getValue().equals(payWayEnum.getValue()) || PayWayEnum.WEIXINPAY.getValue().equals(payWayEnum.getValue()))) {
            throw new ValidationException(PayResultEnum.PAY_WAY_NOT_SUPPORT.getValue(), PayResultEnum.PAY_WAY_NOT_SUPPORT.getComment());
        }
        PayTypeEnum payTypeEnum = PayTypeEnum.valuedOf(requestDto.getPayType());
        if (PayWayEnum.ALIPAY.getValue().equals(payWayEnum.getValue()) &&
                (payTypeEnum == null || !PayTypeEnum.ALI_SCANBAR.getValue().equals(payTypeEnum.getValue()))) {
            throw new ValidationException(PayResultEnum.PAY_WAY_NOT_SUPPORT.getValue(), PayResultEnum.PAY_WAY_NOT_SUPPORT.getComment());
        }
        if (PayWayEnum.WEIXINPAY.getValue().equals(payWayEnum.getValue()) &&
                (payTypeEnum == null || !PayTypeEnum.WX_NATIVE.getValue().equals(payTypeEnum.getValue()))) {
            throw new ValidationException(PayResultEnum.PAY_WAY_NOT_SUPPORT.getValue(), PayResultEnum.PAY_WAY_NOT_SUPPORT.getComment());
        }
        return true;
    }
}