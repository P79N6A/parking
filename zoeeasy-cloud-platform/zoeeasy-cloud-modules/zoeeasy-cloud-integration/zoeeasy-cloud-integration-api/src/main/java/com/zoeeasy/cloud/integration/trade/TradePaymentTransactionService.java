package com.zoeeasy.cloud.integration.trade;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentCompleteRequestDto;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayOrderNotifyResult;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 支付交易业务处理
 *
 * @author walkman
 */
public interface TradePaymentTransactionService {

    /**
     * 支付成功方法
     *
     * @param transactionContext                TCC事务上下文
     * @param tradePaymentCompleteRequestDto    支付记录
     * @param alipayAsyncNotifyResultRequestDto 支付宝异步通知结果
     * @param weChatPayOrderNotifyResult        微信支付异步通知结果
     * @throws BusinessException Exception
     */
    void completeSuccessPaymentOrder(TransactionContext transactionContext,
                                     TradePaymentCompleteRequestDto tradePaymentCompleteRequestDto,
                                     AlipayAsyncNotifyResultRequestDto alipayAsyncNotifyResultRequestDto,
                                     WeChatPayOrderNotifyResult weChatPayOrderNotifyResult) throws BusinessException;


}
