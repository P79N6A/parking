package com.zoeeasy.cloud.pay.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import com.zoeeasy.cloud.pay.exception.TradeBizException;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.validator.PlacePaymentOrderValidator;
import org.springframework.stereotype.Component;

/**
 * 新增地区参数校验
 * Created by song on 2018/9/12.
 */
@Component
public class PlacePaymentOrderValidatorImpl extends ValidatorHandler<PlacePaymentOrderRequestDto> implements PlacePaymentOrderValidator {

    @Override
    public boolean validate(ValidatorContext context, PlacePaymentOrderRequestDto requestDto) {

        BizTypeEnum bizTypeEnum = BizTypeEnum.parse(requestDto.getBizOrderType());
        if (bizTypeEnum == null) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, PaymentConst.PAYMENT_PAY_BIZ_TYPE_INVALID);
        }
        //支付金额 1分-10000 00
        if (requestDto.getOrderAmount().compareTo(PaymentConst.MIN_PAYMENT_AMOUNT_FEN) <= 0
                || requestDto.getOrderAmount().compareTo(PaymentConst.MAX_PAYMENT_AMOUNT_FEN) > 0) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_AMOUNT_INVALID, PaymentConst.PAYMENT_PAY_AMOUNT_INVALID);
        }
        if (requestDto.getOrderTime() == null) {
            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "下单时间错误");
        }
        PayWayEnum payWay = PayWayEnum.valueOf(requestDto.getPayWay());
        if (payWay == null) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, PaymentConst.PAYMENT_PAY_WAY_INVALID);
        }
        PayTypeEnum payType = PayTypeEnum.valuedOf(requestDto.getPayType());
        if (payType == null) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, PaymentConst.PAYMENT_PAY_TYPE_INVALID);
        }
        return true;
    }
}
