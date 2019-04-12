package com.zhuyitech.parking.integration.validatefilter;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.validate.ValidateFilter;
import com.scapegoat.infrastructure.validate.annotaion.ValidateFilterBean;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 停车账单支付请求校验
 *
 * @author walkman
 * @date 2018-06-10
 */
@ValidateFilterBean
public class ParkingOrderPaymentValidateFilter extends ValidateFilter<ParkingOrderPaymentValidateFilter.ParameterMeta> {

    @Override
    protected void process(ParameterMeta parameterMeta, ResultDto resultDto) {

        if (StringUtils.isEmpty(parameterMeta.getOrderNo())) {
            resultDto.makeResult(PayResultEnum.PARKING_RECORD_EMPTY.getValue(), PayResultEnum.PARKING_RECORD_EMPTY.getComment());
            return;
        }
        if (parameterMeta.getPayWay() == null) {
            resultDto.makeResult(PayResultEnum.PAY_WAY_EMPTY.getValue(), PayResultEnum.PAY_WAY_EMPTY.getComment());
            return;
        }
        if (parameterMeta.getPaymentAmount() == null || parameterMeta.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            resultDto.makeResult(PayResultEnum.AMOUNT_ERR.getValue(), PayResultEnum.AMOUNT_ERR.getComment());
            return;
        }

        resultDto.success();
    }

    public static class ParameterMeta {

        /**
         * 停车账单号
         */
        @NotEmpty(message = "停车账单号不能为空")
        @Getter
        @Setter
        private String orderNo;

        /**
         * 支付方式
         * 1  支付宝
         * 2  微信
         * 3  钱包余额
         */
        @NotNull(message = "支付方式不能为空")
        @Getter
        @Setter
        private Integer payWay;

        /**
         * 支付金额
         */
        @Digits(message = "支付金额无效", integer = 100000, fraction = 2)
        @DecimalMin(value = "0", message = "支付金额无效")
        @DecimalMax(value = "100000", message = "支付金额无效")
        @Getter
        @Setter
        private BigDecimal paymentAmount;

        /**
         * SpbillCreateIp
         */
        @NotEmpty(message = "IP不能为空")
        @Getter
        @Setter
        private String spbillCreateIp;

    }
}
