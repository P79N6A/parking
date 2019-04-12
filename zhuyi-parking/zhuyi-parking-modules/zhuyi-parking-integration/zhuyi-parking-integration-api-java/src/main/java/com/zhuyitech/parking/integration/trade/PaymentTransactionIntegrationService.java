package com.zhuyitech.parking.integration.trade;


import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.zoeeasy.cloud.message.payload.CustomerPaySuccessPayload;


/**
 * 支付交易集成服务
 *
 * @author walkman
 */
public interface PaymentTransactionIntegrationService {

    /**
     * 处理平台支付成功消息
     *
     * @param customerPaySuccessPayload
     * @return
     * @throws BusinessException BusinessException
     */
    ResultDto processPaySuccess(CustomerPaySuccessPayload customerPaySuccessPayload) throws BusinessException;
}


