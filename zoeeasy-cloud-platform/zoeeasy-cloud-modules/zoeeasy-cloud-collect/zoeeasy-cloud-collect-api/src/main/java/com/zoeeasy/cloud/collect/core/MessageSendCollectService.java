package com.zoeeasy.cloud.collect.core;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyCallBackRequestDto;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyRequestDto;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceCallBackRequestDto;

/**
 * @author Inmier
 * @date 2019-03-21
 */
public interface MessageSendCollectService {

    /**
     * 发送支付通知结果处理消息
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto sendPaymentNotifyCallBackMessage(PaymentNotifyCallBackRequestDto requestDto);

    /**
     * 发送查询价格结果处理消息
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto sendQueryPriceCallBackMessage(QueryPriceCallBackRequestDto requestDto);

}
