package com.zoeeasy.cloud.integration.service.impl.trade;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.integration.message.MessageSendIntegrationService;
import com.zoeeasy.cloud.integration.message.dto.request.CustomerPaySuccessSendRequestDto;
import com.zoeeasy.cloud.integration.trade.TradePaymentTransactionService;
import com.zoeeasy.cloud.order.transaction.ParkingOrderTransactionService;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentAppointmentOrderRequestDto;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentParkingOrderRequestDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import com.zoeeasy.cloud.pay.exception.TradeBizException;
import com.zoeeasy.cloud.pay.trade.TradePaymentManagerService;
import com.zoeeasy.cloud.pay.trade.TradePaymentQueryService;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.record.AliPayOrderByCustomerOrderNoGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.record.WxPayOrderByCustomerOrderNoGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentByCustomerOrderGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentCompleteRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentUpdatePayStatusRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.record.AliPayOrderResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.record.WxPayOrderResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentOrderResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentRecordResultDto;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayOrderNotifyResult;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

/**
 * 支付交易业务处理
 *
 * @author walkman
 */
@Service("tradePaymentTransactionService")
@Slf4j
public class TradePaymentTransactionServiceImpl implements TradePaymentTransactionService {

    @Autowired
    private MessageSendIntegrationService messageSendIntegrationService;

    @Autowired
    private ParkingOrderTransactionService parkingOrderTransactionService;

    @Autowired
    private TradePaymentManagerService tradePaymentManagerService;

    @Autowired
    private TradePaymentQueryService tradePaymentQueryService;


    /**
     * 支付成功方法
     *
     * @param transactionContext                TCC事务上下文
     * @param tradePaymentCompleteRequestDto    支付记录
     * @param alipayAsyncNotifyResultRequestDto 支付宝异步通知结果
     * @param weChatPayOrderNotifyResult        微信支付异步通知结果
     * @throws BusinessException Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmCompleteSuccessPaymentOrder", cancelMethod = "cancelCompleteSuccessPaymentOrder")
    @Override
    public void completeSuccessPaymentOrder(TransactionContext transactionContext,
                                            TradePaymentCompleteRequestDto tradePaymentCompleteRequestDto,
                                            AlipayAsyncNotifyResultRequestDto alipayAsyncNotifyResultRequestDto,
                                            WeChatPayOrderNotifyResult weChatPayOrderNotifyResult) throws BusinessException {

        log.debug("------completeSuccessPaymentOrder[订单{}完成支付TRYING阶段开始时间{}]------", tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        Date succeedPayTime = new Date();
        String transactionNo = "";
        Integer totalAmount = null;
        PayWayEnum payWayEnum = PayWayEnum.ALIPAY;
        if (alipayAsyncNotifyResultRequestDto != null) {
            // 修改支付记录状态
            succeedPayTime = alipayAsyncNotifyResultRequestDto.getGmtPayment();
            transactionNo = alipayAsyncNotifyResultRequestDto.getTradeNo();
            totalAmount = NumberUtils.amountYuan2Fen(alipayAsyncNotifyResultRequestDto.getTotalAmount()).intValue();
            payWayEnum = PayWayEnum.ALIPAY;
        } else if (weChatPayOrderNotifyResult != null) {
            try {
                succeedPayTime = DateUtils.parseDate(weChatPayOrderNotifyResult.getTimeEnd(), "yyyyMMddHHmmss");
            } catch (ParseException e) {
                log.error("支付成功时间解析失败:{}", e.getMessage());
            }
            transactionNo = weChatPayOrderNotifyResult.getTransactionId();
            totalAmount = weChatPayOrderNotifyResult.getTotalFee();
            payWayEnum = PayWayEnum.WEIXINPAY;
        }
        //更新支付记录订单状态
        TradePaymentUpdatePayStatusRequestDto tradePaymentRecordUpdateRequestDto = new TradePaymentUpdatePayStatusRequestDto();
        tradePaymentRecordUpdateRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentRecordUpdateRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        tradePaymentRecordUpdateRequestDto.setTransactionNo(transactionNo);
        tradePaymentRecordUpdateRequestDto.setSuccessPayTime(succeedPayTime);
        //等待支付确认
        tradePaymentRecordUpdateRequestDto.setPayStatus(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue());
        tradePaymentManagerService.updatePayStatusTradePaymentRecord(tradePaymentRecordUpdateRequestDto);

        //获取支付订单并更新
        TradePaymentByCustomerOrderGetRequestDto tradePaymentOrderGetRequestDto = new TradePaymentByCustomerOrderGetRequestDto();
        tradePaymentOrderGetRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentOrderGetRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        ObjectResultDto<TradePaymentOrderResultDto> tradePaymentOrderResultDto = tradePaymentManagerService.getTradePaymentOrder(tradePaymentOrderGetRequestDto);
        if (tradePaymentOrderResultDto.isFailed() || null == tradePaymentOrderResultDto.getData()) {
            log.error("非法订单，订单{}不存在", tradePaymentCompleteRequestDto.getOrderNo());
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_PAY_ORDER_NOT_FOUND);
        }
        //更新支付订单状态
        TradePaymentUpdatePayStatusRequestDto tradePaymentOrderUpdateRequestDto = new TradePaymentUpdatePayStatusRequestDto();
        tradePaymentOrderUpdateRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentOrderUpdateRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        tradePaymentOrderUpdateRequestDto.setTransactionNo(transactionNo);
        tradePaymentOrderUpdateRequestDto.setSuccessPayTime(succeedPayTime);
        tradePaymentOrderUpdateRequestDto.setPayStatus(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue());
        tradePaymentManagerService.updatePayStatusTradePaymentOrder(tradePaymentOrderUpdateRequestDto);
        TradePaymentOrderResultDto tradePaymentOrder = tradePaymentOrderResultDto.getData();
        //会员充值
        if (BizTypeEnum.RECHARGE.getValue().equals(tradePaymentCompleteRequestDto.getBizOrderType())) {
            // 充业务ANY平台处理
        } else if (BizTypeEnum.PAYMENT.getValue().equals(tradePaymentOrder.getBizOrderType())) {
            //停车账单支付
            log.debug("------completeSuccessPaymentParkingOrder[订单{}完成支付修改停车记录开始时间{}]------",
                    tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            CompletePaymentParkingOrderRequestDto completePaymentParkingOrderRequestDto = new CompletePaymentParkingOrderRequestDto();
            completePaymentParkingOrderRequestDto.setOrderNo(tradePaymentOrder.getBizOrderNo());
            completePaymentParkingOrderRequestDto.setActualAmount(totalAmount);
            completePaymentParkingOrderRequestDto.setSucceedPayTime(succeedPayTime);
            completePaymentParkingOrderRequestDto.setPayOrderNo(tradePaymentOrder.getOrderNo());
            completePaymentParkingOrderRequestDto.setPayedUserId(tradePaymentOrder.getCustomerUserId());
            completePaymentParkingOrderRequestDto.setPayWay(tradePaymentOrder.getPayWay());
            completePaymentParkingOrderRequestDto.setPayType(tradePaymentOrder.getPayType());
            parkingOrderTransactionService.completeSuccessPaymentParkingOrder(transactionContext, completePaymentParkingOrderRequestDto);
            log.debug("------completeSuccessPaymentParkingOrder[订单{}完成支付修改停车记录结束时间{}]------",
                    tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        } else if (BizTypeEnum.APPOINTMENT.getValue().equals(tradePaymentOrder.getBizOrderType())) {
            //预约订单支付
            log.debug("------completeSuccessPaymentAppointmentOrder[订单{}完成支付修改预约订单开始时间{}]------",
                    tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            CompletePaymentAppointmentOrderRequestDto completePaymentAppointmentOrderRequestDto = new CompletePaymentAppointmentOrderRequestDto();
            completePaymentAppointmentOrderRequestDto.setOrderNo(tradePaymentOrder.getBizOrderNo());
            completePaymentAppointmentOrderRequestDto.setActualAmount(totalAmount);
            completePaymentAppointmentOrderRequestDto.setSucceedPayTime(succeedPayTime);
            completePaymentAppointmentOrderRequestDto.setPayOrderNo(tradePaymentOrder.getOrderNo());
            completePaymentAppointmentOrderRequestDto.setPayWay(tradePaymentOrder.getPayWay());
            completePaymentAppointmentOrderRequestDto.setPayType(tradePaymentOrder.getPayType());
            parkingOrderTransactionService.completeSuccessPaymentAppointmentOrder(transactionContext, completePaymentAppointmentOrderRequestDto);
            log.debug("------completeSuccessPaymentAppointmentOrder[订单{}完成支付修改预约订单结束时间{}]------",
                    tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
        log.debug("------completeSuccessPaymentOrder[订单{}完成支付TRYING阶段结束时间{}]------",
                tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    /**
     * @param transactionContext                transactionContext
     * @param tradePaymentCompleteRequestDto    tradePaymentRecord
     * @param alipayAsyncNotifyResultRequestDto alipayAsyncNotifyResultRequestDto
     * @param weChatPayOrderNotifyResult        weChatPayOrderNotifyResult
     * @throws BusinessException Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void confirmCompleteSuccessPaymentOrder(TransactionContext transactionContext,
                                                   TradePaymentCompleteRequestDto tradePaymentCompleteRequestDto,
                                                   AlipayAsyncNotifyResultRequestDto alipayAsyncNotifyResultRequestDto,
                                                   WeChatPayOrderNotifyResult weChatPayOrderNotifyResult
    ) throws BusinessException {

        log.debug("------confirmCompleteSuccessPaymentOrder[订单{}完成支付CONFIRMING阶段开始时间{}]------",
                tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
//         修改支付记录状态
        Date succeedPayTime = new Date();
        Integer totalAmount = null;
        String transactionNo = "";
        if (alipayAsyncNotifyResultRequestDto != null) {
            // 修改支付记录状态
            succeedPayTime = alipayAsyncNotifyResultRequestDto.getGmtPayment();
            transactionNo = alipayAsyncNotifyResultRequestDto.getTradeNo();
            totalAmount = NumberUtils.amountYuan2FenInt(alipayAsyncNotifyResultRequestDto.getTotalAmount());
        } else if (weChatPayOrderNotifyResult != null) {
            try {
                succeedPayTime = DateUtils.parseDate(weChatPayOrderNotifyResult.getTimeEnd(), "yyyyMMddHHmmss");
            } catch (ParseException e) {
                log.error("支付成功时间解析失败:{}", e.getMessage());
            }
            transactionNo = weChatPayOrderNotifyResult.getTransactionId();
            totalAmount = weChatPayOrderNotifyResult.getTotalFee();
        }
        TradePaymentUpdatePayStatusRequestDto tradePaymentUpdateRequestDto = new TradePaymentUpdatePayStatusRequestDto();
        tradePaymentUpdateRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentUpdateRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        tradePaymentUpdateRequestDto.setTransactionNo(transactionNo);
        tradePaymentUpdateRequestDto.setSuccessPayTime(succeedPayTime);
        tradePaymentUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
        tradePaymentManagerService.updatePayStatusTradePaymentRecord(tradePaymentUpdateRequestDto);

        //修改支付订单状态
        TradePaymentByCustomerOrderGetRequestDto tradePaymentOrderGetRequestDto = new TradePaymentByCustomerOrderGetRequestDto();
        tradePaymentOrderGetRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentOrderGetRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        ObjectResultDto<TradePaymentOrderResultDto> tradePaymentOrderResultDto = tradePaymentManagerService.getTradePaymentOrder(tradePaymentOrderGetRequestDto);
        if (tradePaymentOrderResultDto.isFailed() || null == tradePaymentOrderResultDto.getData()) {
            log.error("非法订单，订单{}不存在", tradePaymentCompleteRequestDto.getOrderNo());
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_PAY_ORDER_NOT_FOUND);
        }

        TradePaymentUpdatePayStatusRequestDto tradePaymentOrderUpdateRequestDto = new TradePaymentUpdatePayStatusRequestDto();
        tradePaymentOrderUpdateRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentOrderUpdateRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        tradePaymentOrderUpdateRequestDto.setTransactionNo(transactionNo);
        tradePaymentOrderUpdateRequestDto.setSuccessPayTime(succeedPayTime);
        tradePaymentOrderUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
        tradePaymentManagerService.updatePayStatusTradePaymentOrder(tradePaymentOrderUpdateRequestDto);
        TradePaymentOrderResultDto tradePaymentOrder = tradePaymentOrderResultDto.getData();
        //支付宝订单处理
        if (alipayAsyncNotifyResultRequestDto != null) {
            //根据tradeNo,outTradeNo获取支付宝订单
            AliPayOrderByCustomerOrderNoGetRequestDto aliPayOrderGetRequestDto = new AliPayOrderByCustomerOrderNoGetRequestDto();
            aliPayOrderGetRequestDto.setCustomerId(tradePaymentCompleteRequestDto.getCustomerUserId());
            aliPayOrderGetRequestDto.setOutOrderNo(alipayAsyncNotifyResultRequestDto.getOutTradeNo());
            ObjectResultDto<AliPayOrderResultDto> alipayObject = tradePaymentQueryService.getAliPayByOutOrderNo(aliPayOrderGetRequestDto);
            if (alipayObject.isFailed() || null == alipayObject.getData()) {
                log.error("非法订单，支付宝订单{}不存在", alipayAsyncNotifyResultRequestDto.getOutTradeNo());
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_ALIPAY_ORDER_NOT_FOUND);
            }
            ResultDto resultDto = tradePaymentManagerService.updateAlipayOrder(alipayAsyncNotifyResultRequestDto);
            if (resultDto.isFailed()) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_ALIPAY_ORDER_UPDATE_FAILED);
            }
        }
        if (weChatPayOrderNotifyResult != null) {
            //根据transactionId,outTradeNo获取微信订单
            WxPayOrderByCustomerOrderNoGetRequestDto wxPayOrderGetRequestDto = new WxPayOrderByCustomerOrderNoGetRequestDto();
            wxPayOrderGetRequestDto.setCustomerId(tradePaymentCompleteRequestDto.getCustomerUserId());
            wxPayOrderGetRequestDto.setOutOrderNo(weChatPayOrderNotifyResult.getOutTradeNo());
            ObjectResultDto<WxPayOrderResultDto> weixinObject = tradePaymentQueryService.getWeiXinPayByOutOrderNo(wxPayOrderGetRequestDto);
            if (weixinObject.isFailed() || null == weixinObject.getData()) {
                log.error("非法订单，微信支付订单{}不存在", weChatPayOrderNotifyResult.getOutTradeNo());
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_WXPAY_ORDER_NOT_FOUND);
            }
            ResultDto resultDto = tradePaymentManagerService.updateWeixinOrder(weChatPayOrderNotifyResult);
            if (resultDto.isFailed()) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_WXPAY_ORDER_UPDATE_FAILED);
            }
        }
        //发送用户支付成功通知消息
        CustomerPaySuccessSendRequestDto customerPaySuccessSendRequestDto = new CustomerPaySuccessSendRequestDto();
        customerPaySuccessSendRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        customerPaySuccessSendRequestDto.setBizOrderType(tradePaymentCompleteRequestDto.getBizOrderType());
        customerPaySuccessSendRequestDto.setBizOrderNo(tradePaymentCompleteRequestDto.getBizOrderNo());
        customerPaySuccessSendRequestDto.setActualAmount(totalAmount);
        customerPaySuccessSendRequestDto.setPayOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        customerPaySuccessSendRequestDto.setTransactionNo(transactionNo);
        customerPaySuccessSendRequestDto.setSucceedPayTime(succeedPayTime);
        customerPaySuccessSendRequestDto.setPayWay(tradePaymentCompleteRequestDto.getPayWay());
        customerPaySuccessSendRequestDto.setPayType(tradePaymentCompleteRequestDto.getPayType());
        ResultDto sendResultDto = messageSendIntegrationService.sendCustomerPaySuccessMessage(customerPaySuccessSendRequestDto);
        if (sendResultDto.isFailed()) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, PaymentConst.PAYMENT_MESSAGE_SEND_FAILED);
        }
        log.debug("------confirmCompleteSuccessPaymentOrder[订单{}完成支付CONFIRMING阶段结束时间{}]------", tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    /**
     * @param transactionContext                transactionContext
     * @param tradePaymentCompleteRequestDto    tradePaymentCompleteRequestDto
     * @param alipayAsyncNotifyResultRequestDto alipayAsyncNotifyResultRequestDto
     * @param weChatPayOrderNotifyResult        weChatPayOrderNotifyResult
     * @throws Exception Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelCompleteSuccessPaymentOrder(TransactionContext transactionContext,
                                                  TradePaymentCompleteRequestDto tradePaymentCompleteRequestDto,
                                                  AlipayAsyncNotifyResultRequestDto alipayAsyncNotifyResultRequestDto,
                                                  WeChatPayOrderNotifyResult weChatPayOrderNotifyResult
    ) throws BusinessException {

        log.debug("------cancelCompleteSuccessPaymentOrder[订单{}完成支付CANCELING阶段开始时间{}]------", tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        //根据银行订单号获取支付信息
        //数据库数据
        TradePaymentByCustomerOrderGetRequestDto tradePaymentRecordGetRequestDto = new TradePaymentByCustomerOrderGetRequestDto();
        tradePaymentRecordGetRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentRecordGetRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        ObjectResultDto<TradePaymentRecordResultDto> existTradePaymentRecordObject = tradePaymentManagerService.getTradePaymentRecord(tradePaymentRecordGetRequestDto);
        if (existTradePaymentRecordObject.isFailed() || null == existTradePaymentRecordObject.getData()) {
            log.error("平台支付订单不存在", tradePaymentCompleteRequestDto.getOrderNo());
            return;
        }
        TradePaymentRecordResultDto existTradePaymentRecord = existTradePaymentRecordObject.getData();
//        幂等判断
        //如果支付成功或非等待支付结果状态则不执行取消
        if (PayStatusEnum.PAY_SUCCESS.getValue().equals(existTradePaymentRecord.getStatus())
                || !PayStatusEnum.WAITING_PAYMENT_RESULT.getValue().equals(existTradePaymentRecord.getStatus())) {
            log.info("订单状态：{}，不能执行取消动作", existTradePaymentRecord.getStatus());
            return;
        }
        Date succeedPayTime = new Date();
        String transactionNo = "";
        //修改支付记录状态
        if (alipayAsyncNotifyResultRequestDto != null) {
            // 修改支付记录状态
            succeedPayTime = alipayAsyncNotifyResultRequestDto.getGmtPayment();
            transactionNo = alipayAsyncNotifyResultRequestDto.getTradeNo();
        } else if (weChatPayOrderNotifyResult != null) {
            try {
                succeedPayTime = DateUtils.parseDate(weChatPayOrderNotifyResult.getTimeEnd(), "yyyyMMddHHmmss");
            } catch (ParseException e) {
                throw new BusinessException("", e);
            }
            transactionNo = weChatPayOrderNotifyResult.getTransactionId();
        }
        TradePaymentUpdatePayStatusRequestDto tradePaymentUpdateRequestDto = new TradePaymentUpdatePayStatusRequestDto();
        tradePaymentUpdateRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentUpdateRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        tradePaymentUpdateRequestDto.setTransactionNo(transactionNo);
        tradePaymentUpdateRequestDto.setSuccessPayTime(succeedPayTime);
        tradePaymentUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_WAITING.getValue());
        tradePaymentManagerService.updatePayStatusTradePaymentRecord(tradePaymentUpdateRequestDto);

//      修改支付订单状态
        TradePaymentByCustomerOrderGetRequestDto tradePaymentOrderGetRequestDto = new TradePaymentByCustomerOrderGetRequestDto();
        tradePaymentOrderGetRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
        tradePaymentOrderGetRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
        ObjectResultDto<TradePaymentOrderResultDto> tradePaymentOrderResultDto = tradePaymentManagerService.getTradePaymentOrder(tradePaymentOrderGetRequestDto);
        if (tradePaymentOrderResultDto.isSuccess() && tradePaymentOrderResultDto.getData() != null) {
            TradePaymentUpdatePayStatusRequestDto tradePaymentOrderUpdateRequestDto = new TradePaymentUpdatePayStatusRequestDto();
            tradePaymentOrderUpdateRequestDto.setCustomerUserId(tradePaymentCompleteRequestDto.getCustomerUserId());
            tradePaymentOrderUpdateRequestDto.setOrderNo(tradePaymentCompleteRequestDto.getOrderNo());
            tradePaymentOrderUpdateRequestDto.setTransactionNo(transactionNo);
            tradePaymentOrderUpdateRequestDto.setSuccessPayTime(succeedPayTime);
            tradePaymentOrderUpdateRequestDto.setPayStatus(PayStatusEnum.PAY_WAITING.getValue());
            tradePaymentManagerService.updatePayStatusTradePaymentOrder(tradePaymentOrderUpdateRequestDto);
        }
        log.debug("------cancelCompleteSuccessPaymentOrder[订单{}完成支付CANCELING阶段结束时间{}]------", tradePaymentCompleteRequestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

}
