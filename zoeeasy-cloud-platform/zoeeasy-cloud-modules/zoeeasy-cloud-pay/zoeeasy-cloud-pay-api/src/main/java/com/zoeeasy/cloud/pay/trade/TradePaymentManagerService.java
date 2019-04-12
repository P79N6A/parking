package com.zoeeasy.cloud.pay.trade;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pay.exception.TradeBizException;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentByCustomerOrderGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentOrderGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentRecordGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentUpdatePayStatusRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.order.PlacePaymentOrderResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentOrderResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentRecordResultDto;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayOrderNotifyResult;


/**
 * 支付交易服务
 *
 * @author walkman
 * @since 2018-01-11
 */
public interface TradePaymentManagerService {

    /**
     * 支付订单下单
     *
     * @param requestDto 请求参数
     * @return
     * @throws TradeBizException   交易业务异常
     * @throws ValidationException 业务验证异常
     */
    ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrder(PlacePaymentOrderRequestDto requestDto) throws ValidationException, TradeBizException;

    /**
     * 获取支付记录
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<TradePaymentRecordResultDto> getTradePaymentRecord(TradePaymentRecordGetRequestDto requestDto);

    /**
     * 根据支付用户ID、支付订单号获取支付记录
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<TradePaymentRecordResultDto> getTradePaymentRecord(TradePaymentByCustomerOrderGetRequestDto requestDto);

    /**
     * 根据支付用户ID、业务订单号获取支付订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<TradePaymentOrderResultDto> getTradePaymentOrder(TradePaymentOrderGetRequestDto requestDto);

    /**
     * 根据支付用户ID、支付订单号获取支付订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<TradePaymentOrderResultDto> getTradePaymentOrder(TradePaymentByCustomerOrderGetRequestDto requestDto);

    /**
     * 修改支付订单支付状态tradePaymentRecord
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updatePayStatusTradePaymentRecord(TradePaymentUpdatePayStatusRequestDto requestDto) throws ValidationException, TradeBizException;

    /**
     * 修改支付订单支付状态tradePaymentOrder
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updatePayStatusTradePaymentOrder(TradePaymentUpdatePayStatusRequestDto requestDto) throws ValidationException, TradeBizException;

    /**
     * 更新支付宝订单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateAlipayOrder(AlipayAsyncNotifyResultRequestDto requestDto) throws ValidationException, TradeBizException;

    /**
     * 更新微信订单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateWeixinOrder(WeChatPayOrderNotifyResult requestDto) throws ValidationException, TradeBizException;

}
