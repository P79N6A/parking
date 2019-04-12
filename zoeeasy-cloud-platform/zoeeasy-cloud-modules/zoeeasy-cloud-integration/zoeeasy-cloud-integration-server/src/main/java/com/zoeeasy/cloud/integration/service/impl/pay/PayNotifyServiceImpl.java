package com.zoeeasy.cloud.integration.service.impl.pay;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PublicEnum;
import com.zoeeasy.cloud.integration.enums.IntegrationResultEnum;
import com.zoeeasy.cloud.integration.pay.PayNotifyService;
import com.zoeeasy.cloud.integration.trade.TradePaymentTransactionService;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import com.zoeeasy.cloud.pay.enums.AlipayTradeStatusEnum;
import com.zoeeasy.cloud.pay.enums.WeixinMessageType;
import com.zoeeasy.cloud.pay.exception.TradeBizException;
import com.zoeeasy.cloud.pay.trade.AlipayMessageLogService;
import com.zoeeasy.cloud.pay.trade.TradePaymentManagerService;
import com.zoeeasy.cloud.pay.trade.WeChatMessageLogService;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.message.AlipayMessageLogAddRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.message.AlipayMessageLogGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.message.WeChatMessageLogAddRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentCompleteRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentRecordGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.alipay.AlipayAsyncNotifyResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.message.AlipayMessageLogResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentRecordResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.WeixinAsyncNotifyResultDto;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayOrderNotifyResult;
import com.zoeeasy.cloud.pay.wechat.service.WeChatPayService;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.CancellingException;
import org.mengyun.tcctransaction.ConfirmingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 支付回调服务
 *
 * @author zwq
 * @date 2018-10-16
 */
@Service(value = "payNotifyService")
@Slf4j
public class PayNotifyServiceImpl implements PayNotifyService {

    @Autowired
    private AlipayMessageLogService alipayMessageLogService;

    @Autowired
    private WeChatMessageLogService weChatMessageLogService;

    @Autowired
    private TradePaymentTransactionService tradePaymentTransactionService;

    @Autowired
    private WeChatPayService weChatPayService;

    @Autowired
    private TradePaymentManagerService tradePaymentManagerService;

    /**
     * 支付宝异步通知处理
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ObjectResultDto<AlipayAsyncNotifyResultDto> processAliPayNotify(AlipayAsyncNotifyResultRequestDto requestDto) {
        ObjectResultDto<AlipayAsyncNotifyResultDto> resultDto = new ObjectResultDto<>();
        log.debug("接收到支付宝支付通知{}", requestDto.toString());
        try {

            AlipayMessageLogAddRequestDto alipayMessageLogAddRequestDto = new AlipayMessageLogAddRequestDto();
            alipayMessageLogAddRequestDto.setAppId(requestDto.getAppId());
            alipayMessageLogAddRequestDto.setIp(requestDto.getIp());
            alipayMessageLogAddRequestDto.setNotifyId(requestDto.getNotifyId());
            alipayMessageLogAddRequestDto.setNotifyTime(requestDto.getNotifyTime());
            alipayMessageLogAddRequestDto.setNotifyType(requestDto.getNotifyType());
            alipayMessageLogAddRequestDto.setOutBizNo(requestDto.getOutBizNo());
            alipayMessageLogAddRequestDto.setOutTradeNo(requestDto.getOutTradeNo());
            alipayMessageLogAddRequestDto.setTradeNo(requestDto.getTradeNo());
            alipayMessageLogAddRequestDto.setUrl(requestDto.getUrl());
            alipayMessageLogAddRequestDto.setContent(JSON.toJSONString(requestDto));

            AlipayMessageLogGetRequestDto alipayMessageLogGetRequestDto = new AlipayMessageLogGetRequestDto();
            alipayMessageLogGetRequestDto.setNotifyId(requestDto.getNotifyId());
            alipayMessageLogGetRequestDto.setStatus(PublicEnum.YES.getValue());
            ObjectResultDto<AlipayMessageLogResultDto> alipayMessageLogObjectResultDto = alipayMessageLogService.getMessageLog(alipayMessageLogGetRequestDto);
            if (alipayMessageLogObjectResultDto.isSuccess() && null != alipayMessageLogObjectResultDto.getData()) {
                alipayMessageLogAddRequestDto.setStatus(PublicEnum.YES.getValue());
                alipayMessageLogAddRequestDto.setResult("支付宝异步通知已经成功处理");
                ResultDto addRes = alipayMessageLogService.addMessageLog(alipayMessageLogAddRequestDto);
                if (addRes.isFailed()) {
                    return resultDto.makeResult(IntegrationResultEnum.ADD_ALIPAYMESSAGELOG_ERR.getValue(), IntegrationResultEnum.ADD_ALIPAYMESSAGELOG_ERR.getComment());
                }
                resultDto.success();
                return resultDto;
            }

            //支付订单号
            String payOrderNo = requestDto.getOutTradeNo();
            log.debug("------[接收到要处理的支付订单{}]--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

            //根据支付订单号获取支付记录
            TradePaymentRecordGetRequestDto tradePaymentRecordGetRequestDto = new TradePaymentRecordGetRequestDto();
            tradePaymentRecordGetRequestDto.setOrderNo(payOrderNo);
            ObjectResultDto<TradePaymentRecordResultDto> tradePaymentRecordObject = tradePaymentManagerService.getTradePaymentRecord(tradePaymentRecordGetRequestDto);

            if (tradePaymentRecordObject.isFailed() || null == tradePaymentRecordObject.getData()) {
                log.error("非法订单，订单{}不存在", payOrderNo);
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_PAY_ORDER_NOT_FOUND);
            }
            TradePaymentRecordResultDto tradePaymentRecord = tradePaymentRecordObject.getData();

            // 幂等判断
            if (PayStatusEnum.PAY_SUCCESS.getValue().equals(tradePaymentRecord.getStatus())) {
                log.info("订单{}为成功状态,不做业务处理", payOrderNo);
                resultDto.success();
                return resultDto;
            }
            if (!PayStatusEnum.PAY_WAITING.getValue().equals(tradePaymentRecord.getStatus())) {
                log.info("订单{}状态为非等待支付状态,不做业务处理", payOrderNo);
                resultDto.success();
                return resultDto;
            }
            //是否支付成功
            boolean paySuccess = false;
            if (AlipayTradeStatusEnum.TRADE_SUCCESS.name().equals(requestDto.getTradeStatus())) {
                paySuccess = true;
            } else if (AlipayTradeStatusEnum.TRADE_FINISHED.name().equals(requestDto.getTradeStatus())) {
                paySuccess = true;
            } else if (AlipayTradeStatusEnum.TRADE_CLOSED.name().equals(requestDto.getTradeStatus())) {
                paySuccess = false;
            } else {
                return resultDto.failed();
            }
            if (paySuccess) {

                TradePaymentCompleteRequestDto tradePaymentCompleteRequestDto = new TradePaymentCompleteRequestDto();
                //支付宝返回订单支付成功
                tradePaymentCompleteRequestDto.setCustomerUserId(tradePaymentRecord.getCustomerUserId());
                tradePaymentCompleteRequestDto.setBizOrderType(tradePaymentRecord.getBizOrderType());
                tradePaymentCompleteRequestDto.setBizOrderNo(tradePaymentRecord.getBizOrderNo());
                tradePaymentCompleteRequestDto.setOrderFrom(tradePaymentRecord.getOrderFrom());
                tradePaymentCompleteRequestDto.setOrderNo(tradePaymentRecord.getOrderNo());
                tradePaymentCompleteRequestDto.setTransactionNo(tradePaymentRecord.getTransactionNo());
                tradePaymentCompleteRequestDto.setPayWay(tradePaymentRecord.getPayWay());
                tradePaymentCompleteRequestDto.setPayType(tradePaymentRecord.getPayType());
                log.debug("==>[开始处理支付成功的支付订单{}结果]--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                try {
                    tradePaymentTransactionService.completeSuccessPaymentOrder(null,
                            tradePaymentCompleteRequestDto,
                            requestDto, null
                    );
                } catch (Throwable e) {
                    //other exceptions throws at TRYING stage.
                    //you can retry or cancel the operation.
                    log.error("==>处理支付成功的支付宝付宝订单结果异常:" + e.getMessage());
                    throw new TradeBizException(TradeBizException.TRADE_SYSTEM_ERROR, "交易系统异常,处理支付结果失败");
                }
                alipayMessageLogAddRequestDto.setStatus(PublicEnum.YES.getValue());
                alipayMessageLogAddRequestDto.setResult("支付宝异步通知处理成功");
                log.debug("==>结束处理支付成功的付宝订单结果--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            } else {
                //支付失败
                log.debug("==>开始处理支付失败的订单结果");
                alipayMessageLogAddRequestDto.setStatus(PublicEnum.NO.getValue());
                alipayMessageLogAddRequestDto.setResult("支付宝异步通知处理失败");
            }
            ResultDto addRes = alipayMessageLogService.addMessageLog(alipayMessageLogAddRequestDto);
            if (addRes.isFailed()) {
                return resultDto.makeResult(IntegrationResultEnum.ADD_ALIPAYMESSAGELOG_ERR.getValue(), IntegrationResultEnum.ADD_ALIPAYMESSAGELOG_ERR.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("支付宝异步通知处理失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 微信异步通知处理
     *
     * @param weixinAsyncNotifyResultRequestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<WeixinAsyncNotifyResultDto> processWeixinNotify(WeixinAsyncNotifyResultRequestDto weixinAsyncNotifyResultRequestDto) {

        WeixinAsyncNotifyResultDto weixinAsyncNotifyResultDto = new WeixinAsyncNotifyResultDto();
        ObjectResultDto<WeixinAsyncNotifyResultDto> resultDto = new ObjectResultDto<>();
        log.debug("接收到微信支付通知{}", weixinAsyncNotifyResultRequestDto.toString());
        try {

            ObjectResultDto<WeChatPayOrderNotifyResult> objectResultEx = weChatPayService.orderNotify(weixinAsyncNotifyResultRequestDto.getWeixinReturnXml());
            if (objectResultEx.isFailed() || null == objectResultEx.getData()) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_WXPAY_PARSE_FAILED);
            }
            WeChatPayOrderNotifyResult weChatPayOrderNotifyResult = objectResultEx.getData();

            //支付订单号
            String payOrderNo = weChatPayOrderNotifyResult.getOutTradeNo();
            log.debug("------[接收到要处理的支付订单{}]--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

            //根据支付订单号获取支付信息
            TradePaymentRecordGetRequestDto paymentRecordGetByOrderNoRequestDto = new TradePaymentRecordGetRequestDto();
            paymentRecordGetByOrderNoRequestDto.setOrderNo(payOrderNo);
            ObjectResultDto<TradePaymentRecordResultDto> tradePaymentRecordObject = tradePaymentManagerService.getTradePaymentRecord(paymentRecordGetByOrderNoRequestDto);

            if (tradePaymentRecordObject.isFailed() || null == tradePaymentRecordObject.getData()) {
                log.error("非法订单，订单{}不存在", payOrderNo);
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_PAY_ORDER_NOT_FOUND);
            }
            TradePaymentRecordResultDto tradePaymentRecord = tradePaymentRecordObject.getData();
            String processWeixinNotifyResult = "微信异步通知处理成功";
            //是否需要处理通知
            boolean needProcess = true;
            // 幂等判断
            if (PayStatusEnum.PAY_SUCCESS.getValue().equals(tradePaymentRecord.getStatus())) {
                log.info("订单{}为成功状态,不做业务处理", payOrderNo);
                processWeixinNotifyResult = String.format("订单%s为成功状态,不做业务处理", payOrderNo);
                needProcess = false;
            }
            if (!PayStatusEnum.PAY_WAITING.getValue().equals(tradePaymentRecord.getStatus())) {
                log.info("订单{}状态为非等待支付状态,不做业务处理", payOrderNo);
                processWeixinNotifyResult = String.format("订单%s状态为非等待支付状态,不做业务处理", payOrderNo);
                needProcess = false;
            }

            if (needProcess) {
                //交易状态
                boolean paySuccess = false;
                if (weChatPayOrderNotifyResult.getResultCode().equals("SUCCESS")) {
                    paySuccess = true;
                } else if (("FAIL").equalsIgnoreCase(weChatPayOrderNotifyResult.getResultCode())) {
                    processWeixinNotifyResult = String.format("订单%s,微信的处理结果是%s", payOrderNo, weChatPayOrderNotifyResult.getErrCodeDes());
                    paySuccess = false;
                }
                if (paySuccess) {
                    //微信或支付宝返回订单支付成功
                    log.debug("------[开始处理支付成功的微信支付订单结果{}]--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                    TradePaymentCompleteRequestDto tradePaymentCompleteRequestDto = new TradePaymentCompleteRequestDto();
                    //微信或支付宝返回订单支付成功
                    tradePaymentCompleteRequestDto.setCustomerUserId(tradePaymentRecord.getCustomerUserId());
                    tradePaymentCompleteRequestDto.setBizOrderType(tradePaymentRecord.getBizOrderType());
                    tradePaymentCompleteRequestDto.setBizOrderNo(tradePaymentRecord.getBizOrderNo());
                    tradePaymentCompleteRequestDto.setOrderFrom(tradePaymentRecord.getOrderFrom());
                    tradePaymentCompleteRequestDto.setOrderNo(tradePaymentRecord.getOrderNo());
                    tradePaymentCompleteRequestDto.setTransactionNo(weChatPayOrderNotifyResult.getTransactionId());
                    tradePaymentCompleteRequestDto.setPayWay(tradePaymentRecord.getPayWay());
                    tradePaymentCompleteRequestDto.setPayType(tradePaymentRecord.getPayType());
                    try {

                        tradePaymentTransactionService.completeSuccessPaymentOrder(null,
                                tradePaymentCompleteRequestDto,
                                null, weChatPayOrderNotifyResult
                        );
                    } catch (ConfirmingException confirmingException) {
                        //exception throws with the tcc transaction status is CONFIRMING,
                        //when tcc transaction is confirming status,
                        processWeixinNotifyResult = String.format("订单%s,tcc transaction recovery will try to confirm the whole transaction to ensure eventually consistent：%s", payOrderNo, confirmingException.getMessage());
                        // the tcc transaction recovery will try to confirm the whole transaction to ensure eventually consistent.
                    } catch (CancellingException cancellingException) {
                        //exception throws with the tcc transaction status is CANCELLING,
                        //when tcc transaction is under CANCELLING status,
                        // the tcc transaction recovery will try to cancel the whole transaction to ensure eventually consistent.
                        processWeixinNotifyResult = String.format("订单%stcc transaction recovery will try to cancel the whole transaction to ensure eventually consistent：%s", payOrderNo, cancellingException.getMessage());
                    } catch (Throwable e) {
                        //other exceptions throws at TRYING stage.
                        //you can retry or cancel the operation.
                        log.error("==>交易系统异常,处理支付结果失败" + e.getMessage());
                        processWeixinNotifyResult = String.format("订单%s,处理出现异常%s", payOrderNo, e.getMessage());
                        throw new TradeBizException(TradeBizException.TRADE_SYSTEM_ERROR, "交易系统异常,处理支付结果失败");
                    }
                    log.debug("------[处理支付成功的微信支付订单结果结束{}]--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                } else {
                    log.debug("------[开始处理支付失败的订单结果{}]--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                    log.debug("------[开始处理支付失败的订单结果{}]--------[开始处理时间{}]------", payOrderNo, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                }
            }
            //微信异步通知
            WeChatMessageLogAddRequestDto weChatMessageLogAddRequestDto = new WeChatMessageLogAddRequestDto();
            weChatMessageLogAddRequestDto.setMessageType(WeixinMessageType.PAYMENT.getValue());
            weChatMessageLogAddRequestDto.setIp(weixinAsyncNotifyResultRequestDto.getIp());
            weChatMessageLogAddRequestDto.setUrl(weixinAsyncNotifyResultRequestDto.getUrl());
            weChatMessageLogAddRequestDto.setOutTradeNo(weChatPayOrderNotifyResult.getOutTradeNo());
            weChatMessageLogAddRequestDto.setTransactionId(weChatPayOrderNotifyResult.getTransactionId());
            weChatMessageLogAddRequestDto.setContent(weixinAsyncNotifyResultRequestDto.getWeixinReturnXml());
            weChatMessageLogAddRequestDto.setStatus(PublicEnum.YES.getValue());
            weChatMessageLogAddRequestDto.setResult(processWeixinNotifyResult);
            weChatMessageLogService.addMessageLog(weChatMessageLogAddRequestDto);
            weixinAsyncNotifyResultDto.setReturnCode(weChatPayOrderNotifyResult.getReturnCode());
            weixinAsyncNotifyResultDto.setReturnMsg(weChatPayOrderNotifyResult.getReturnMsg());
            resultDto.setData(weixinAsyncNotifyResultDto);
        } catch (Exception e) {
            log.error("微信异步通知处理失败" + e.getMessage(), e);
            resultDto.failed();
        }
        return resultDto.success();

    }
}
