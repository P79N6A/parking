package com.zhuyitech.parking.ucc.trade;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.trade.dto.*;
import com.zoeeasy.cloud.pay.trade.dto.request.order.AppointOrderPayRequestDto;


/**
 * 交易服务(充值，付款)
 *
 * @author walkman
 * @date 2018-01-11
 */
public interface PaymentTransactionService {

    /**
     * 客户充值
     *
     * @param requestDto 请求参数
     * @return
     */
    ObjectResultDto<RechargePlaceResultDto> recharge(RechargePlaceRequestDto requestDto);

    /**
     * 充值确认
     *
     * @param requestDto 请求参数
     * @return
     */
    ObjectResultDto<RechargeConfirmResultDto> rechargeConfirm(RechargeConfirmRequestDto requestDto);

    /**
     * 预约订单支付
     *
     * @param requestDto 请求参数
     * @return
     */
    ObjectResultDto<AppointOrderPaymentResultDto> appointPayment(AppointOrderPayRequestDto requestDto);
}
