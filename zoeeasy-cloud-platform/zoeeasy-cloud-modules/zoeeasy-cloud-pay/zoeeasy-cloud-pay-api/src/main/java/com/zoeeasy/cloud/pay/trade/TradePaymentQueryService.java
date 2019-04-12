package com.zoeeasy.cloud.pay.trade;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.record.*;
import com.zoeeasy.cloud.pay.trade.dto.result.record.*;

/**
 * 用户微信、支付宝支付记录以及充值记录查询
 *
 * @Date: 2018/3/5
 * @author: yuzhicheng
 */
public interface TradePaymentQueryService {

    /**
     * 分页获取微信支付记录
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<WxPayOrderResultDto> getWeiXinPayPagedList(WeiXinPayRecordQueryPageRequestDto requestDto);

    /**
     * 获取微信支付记录列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<WxPayOrderResultDto> getWeiXinPayList(WeiXinPayRecordListGetRequestDto requestDto);

    /**
     * 获取微信支付记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WxPayOrderResultDto> getWeiXinPay(WeiXinPayRecordGetRequestDto requestDto);

    /**
     * 分页获取支付宝支付记录
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AliPayOrderResultDto> getAliPayPagedList(AliPayOrderQueryPageRequestDto requestDto);

    /**
     * 获取支付宝支付记录列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<AliPayOrderResultDto> getAliPayList(AliPayOrderListGetRequestDto requestDto);

    /**
     * 获取支付宝支付记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AliPayOrderResultDto> getAliPay(AliPayOrderGetRequestDto requestDto);

    /**
     * 根据bizOrderType和bizOrderNo查询
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PaymentRecordGetResultDto> getByBizOrderNoAndType(PaymentRecordGetByBizOrderRequestDto requestDto);

    /**
     * 根据订单查询
     *
     * @param requestDto
     * @return
     */
    ListResultDto<PaymentOrderGetByBizOrderResultDto> getPayByBizOrderNo(PaymentOrderGetByBizOrderRequestDto requestDto);

    /**
     * 根据订单查询tradePaymentRecord
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PaymentRecordResultDto> getTradePaymentRecordByOrderNo(PaymentRecordGetByOrderNoRequestDto requestDto);

    /**
     * 根据outOrderNo获取支付宝支付记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AliPayOrderResultDto> getAliPayByOutOrderNo(AliPayOrderByCustomerOrderNoGetRequestDto requestDto);

    /**
     * 根据outOrderNo获取微信支付记录
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WxPayOrderResultDto> getWeiXinPayByOutOrderNo(WxPayOrderByCustomerOrderNoGetRequestDto requestDto);

}
