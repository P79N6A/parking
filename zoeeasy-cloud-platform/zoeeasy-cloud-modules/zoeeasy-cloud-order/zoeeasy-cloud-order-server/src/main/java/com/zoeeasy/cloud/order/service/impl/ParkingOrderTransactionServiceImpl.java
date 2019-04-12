package com.zoeeasy.cloud.order.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.enums.AppointStatusEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.order.domain.ParkingAppointmentOrderEntity;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.exception.ParkingBizException;
import com.zoeeasy.cloud.order.message.MessageSendOrderService;
import com.zoeeasy.cloud.order.message.dot.request.OrderConfirmCallbackRequestDto;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderPayStatusUpdateRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.service.ParkingAppointmentOrderCrudService;
import com.zoeeasy.cloud.order.transaction.ParkingOrderTransactionService;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentAppointmentOrderRequestDto;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentParkingOrderRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 停车订单交易服务
 *
 * @author walkman
 */
@Service("parkingOrderTransactionService")
@Slf4j
public class ParkingOrderTransactionServiceImpl implements ParkingOrderTransactionService {

    @Autowired
    private ParkingAppointmentOrderCrudService parkingAppointmentOrderCrudService;

    @Autowired
    private MessageSendOrderService messageSendOrderService;

    @Autowired
    private PlatformParkingOrderService platformParkingOrderService;

    /**
     * 预约订单支付完成
     *
     * @param transactionContext TCC事务上下文
     * @param requestDto         预约订单支付请求参数
     * @throws ParkingBizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmCompleteSuccessPaymentAppointmentOrder", cancelMethod = "cancelCompleteSuccessPaymentAppointmentOrder")
    public void completeSuccessPaymentAppointmentOrder(TransactionContext transactionContext, CompletePaymentAppointmentOrderRequestDto requestDto) throws ParkingBizException {

        log.debug("------completeSuccessPaymentAppointmentOrder[订单{}完成支付TRYING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        //预约订单
        ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.findByOrderNo(requestDto.getOrderNo(), null);
        if (parkingAppointmentOrderEntity == null) {
            throw new ParkingBizException(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(), OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
        }
        parkingAppointmentOrderEntity.setPayTime(requestDto.getSucceedPayTime());
        parkingAppointmentOrderEntity.setPayStatus(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue());
        parkingAppointmentOrderEntity.setActualPayAmount(requestDto.getActualAmount());
        parkingAppointmentOrderEntity.setPayWay(requestDto.getPayWay());
        parkingAppointmentOrderEntity.setPayType(requestDto.getPayType());

        parkingAppointmentOrderCrudService.updateAppointOrder(parkingAppointmentOrderEntity);

        log.debug("------completeSuccessPaymentAppointmentOrder[订单{}完成支付TRYING阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmCompleteSuccessPaymentAppointmentOrder(TransactionContext transactionContext, CompletePaymentAppointmentOrderRequestDto requestDto) throws ParkingBizException {

        log.debug("------confirmCompleteSuccessPaymentAppointmentOrder[订单{}完成支付CONFIRM阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.findByOrderNo(requestDto.getOrderNo(), null);
        if (parkingAppointmentOrderEntity == null) {
            throw new ParkingBizException(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(), OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
        }
        parkingAppointmentOrderEntity.setPayTime(requestDto.getSucceedPayTime());
        parkingAppointmentOrderEntity.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
        parkingAppointmentOrderEntity.setAppointStatus(AppointStatusEnum.SUCCESS.getValue());
        parkingAppointmentOrderEntity.setActualPayAmount(requestDto.getActualAmount());
        parkingAppointmentOrderEntity.setPayWay(requestDto.getPayWay());
        parkingAppointmentOrderEntity.setPayType(requestDto.getPayType());

        parkingAppointmentOrderCrudService.updateAppointOrder(parkingAppointmentOrderEntity);

        log.debug("------confirmCompleteSuccessPaymentAppointmentOrder[订单{}完成支付CONFIRM阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelCompleteSuccessPaymentAppointmentOrder(TransactionContext transactionContext, CompletePaymentAppointmentOrderRequestDto requestDto) throws ParkingBizException {

        //根据预约订单号获取预约订单
        log.debug("------cancelCompleteSuccessPaymentAppointmentOrder[订单{}完成支付CANCELING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        ParkingAppointmentOrderEntity parkingAppointmentOrderEntity = parkingAppointmentOrderCrudService.findByOrderNo(requestDto.getOrderNo(), null);
        if (parkingAppointmentOrderEntity == null) {
            throw new ParkingBizException(OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(), OrderResultEnum.APPOINT_ORDER_NOT_FOUND.getComment());
        }
        // 幂等判断
        if (PayStatusEnum.PAY_SUCCESS.getValue().equals(parkingAppointmentOrderEntity.getPayStatus())
                || !PayStatusEnum.WAITING_PAYMENT_RESULT.getValue().equals(parkingAppointmentOrderEntity.getPayStatus())) {
            log.info("订单状态：{}，不能执行取消动作", parkingAppointmentOrderEntity.getPayStatus());
            return;
        }
        parkingAppointmentOrderEntity.setPayStatus(PayStatusEnum.PAY_WAITING.getValue());
        parkingAppointmentOrderEntity.setPayTime(requestDto.getSucceedPayTime());
        parkingAppointmentOrderEntity.setActualPayAmount(requestDto.getActualAmount());
        parkingAppointmentOrderEntity.setPayWay(null);
        parkingAppointmentOrderEntity.setPayType(null);

        parkingAppointmentOrderCrudService.updateAppointOrder(parkingAppointmentOrderEntity);

        log.debug("------cancelCompleteSuccessPaymentAppointmentOrder[订单{}完成支付CANCELING阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    /**
     * 停车账单支付完成
     *
     * @param transactionContext transactionContext
     * @param requestDto         requestDto
     * @throws ParkingBizException ParkingBizException
     */
    @Override
    @Compensable(confirmMethod = "confirmCompleteSuccessPaymentParkingOrder", cancelMethod = "cancelCompleteSuccessPaymentParkingOrder")
    @Transactional(rollbackFor = {Exception.class})
    public void completeSuccessPaymentParkingOrder(TransactionContext transactionContext, CompletePaymentParkingOrderRequestDto requestDto) throws ParkingBizException {

        log.debug("------completeSuccessPaymentParkingOrder[订单{}完成支付TRYING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        //停车订单
        ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
        parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
        ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
        if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
            log.error("停车订{}不存在", requestDto.getOrderNo());
            throw new ParkingBizException(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(), OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment());
        }
        ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
        // 幂等判断
        if (PayStatusEnum.PAY_SUCCESS.getValue().equals(parkingOrder.getPayStatus())) {
            log.info("停车订单状态：{}，无需执行CONFIRM操作", parkingOrder.getPayStatus());
            return;
        }
        ParkingOrderPayStatusUpdateRequestDto parkingOrderUpdateForPayRequestDto = new ParkingOrderPayStatusUpdateRequestDto();
        parkingOrderUpdateForPayRequestDto.setOrderNo(parkingOrder.getOrderNo());
        parkingOrderUpdateForPayRequestDto.setPayedUserId(requestDto.getPayedUserId());
        parkingOrderUpdateForPayRequestDto.setPayTime(requestDto.getSucceedPayTime());
        parkingOrderUpdateForPayRequestDto.setPayStatus(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue());
        parkingOrderUpdateForPayRequestDto.setActualPayAmount(requestDto.getActualAmount());
        parkingOrderUpdateForPayRequestDto.setPayWay(requestDto.getPayWay());
        parkingOrderUpdateForPayRequestDto.setPayType(requestDto.getPayType());
        platformParkingOrderService.updateParkingOrder(parkingOrderUpdateForPayRequestDto);

        log.debug("------completeSuccessPaymentParkingOrder[订单{}完成支付TRYING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    /**
     * @param transactionContext transactionContext
     * @param requestDto         requestDto
     * @throws ParkingBizException ParkingBizException
     */
    @Transactional(rollbackFor = {Exception.class})
    public void confirmCompleteSuccessPaymentParkingOrder(TransactionContext transactionContext, CompletePaymentParkingOrderRequestDto requestDto) throws ParkingBizException {

        log.debug("------confirmCompleteSuccessPaymentParkingOrder[订单{}完成支付CONFIRM阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
        parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
        ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
        if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
            throw new ParkingBizException(OrderResultEnum.PARKING_RECORD_NOT_FOUND.getValue(), OrderResultEnum.PARKING_RECORD_NOT_FOUND.getComment());
        }
        ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
        //如果未结算，则以支付成功时间为结算时间
        ParkingOrderPayStatusUpdateRequestDto parkingOrderUpdate = new ParkingOrderPayStatusUpdateRequestDto();
        if (!parkingOrder.getSettle()) {
            parkingOrderUpdate.setSettle(Boolean.TRUE);
            parkingOrderUpdate.setSettleTime(requestDto.getSucceedPayTime());
        }
        parkingOrderUpdate.setActualPayAmount(requestDto.getActualAmount());
        parkingOrderUpdate.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
        parkingOrderUpdate.setPayTime(requestDto.getSucceedPayTime());
        parkingOrderUpdate.setPayedUserId(requestDto.getPayedUserId());
        parkingOrderUpdate.setPayable(Boolean.FALSE);
        parkingOrderUpdate.setNeedPay(Boolean.FALSE);
        //更新订单
        parkingOrderUpdate.setOrderNo(parkingOrder.getOrderNo());
        platformParkingOrderService.updateParkingOrder(parkingOrderUpdate);
        //如果三方平台订单存在，则发送账单支付状态同步消息
        if (StringUtils.isNotEmpty(parkingOrder.getThirdBillNo())) {
            log.warn("&&&&&&&&&&&&&payStart&&&&&&&&&&&");
            OrderConfirmCallbackRequestDto orderConfirmCallbackRequestDto = new OrderConfirmCallbackRequestDto();
            orderConfirmCallbackRequestDto.setThirdBillNo(parkingOrder.getThirdBillNo());
            orderConfirmCallbackRequestDto.setParkingId(parkingOrder.getParkingId());
            orderConfirmCallbackRequestDto.setActualPayAmount(parkingOrder.getActualPayAmount());
            orderConfirmCallbackRequestDto.setSucceedPayTime(requestDto.getSucceedPayTime());
            if (requestDto.getPayWay() != null) {
                if (requestDto.getPayWay().equals(PayWayEnum.ALIPAY.getValue())) {
                    orderConfirmCallbackRequestDto.setPayWay(4);
                } else if (requestDto.getPayWay().equals(PayWayEnum.WEIXINPAY.getValue())) {
                    orderConfirmCallbackRequestDto.setPayWay(5);
                }
                orderConfirmCallbackRequestDto.setPayWay(requestDto.getPayWay());
            }
            log.warn("&&&&&&&&&&&&&payEnd&&&&&&&&&&&" + orderConfirmCallbackRequestDto);
            messageSendOrderService.sendOrderConfirmCallbackMessage(orderConfirmCallbackRequestDto);
        }
    }

    /**
     * @param transactionContext transactionContext
     * @param requestDto         requestDto
     * @throws ParkingBizException ParkingBizException
     */
    @Transactional(rollbackFor = {Exception.class})
    public void cancelCompleteSuccessPaymentParkingOrder(TransactionContext transactionContext, CompletePaymentParkingOrderRequestDto requestDto) throws ParkingBizException {

        //根据停车订单号获取停车记录信息
        log.debug("------cancelCompleteSuccessPaymentParkingOrder[订单{}完成支付CANCELING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
        parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
        ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
        if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
            throw new ParkingBizException(OrderResultEnum.PARKING_RECORD_NOT_FOUND.getValue(), OrderResultEnum.PARKING_RECORD_NOT_FOUND.getComment());
        }
        ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
        // 幂等判断
        if (PayStatusEnum.PAY_SUCCESS.getValue().equals(parkingOrder.getPayStatus())
                || !PayStatusEnum.WAITING_PAYMENT_RESULT.getValue().equals(parkingOrder.getPayStatus())) {
            log.info("订单状态：{}，不能执行取消动作", parkingOrder.getPayStatus());
            return;
        }

        ParkingOrderPayStatusUpdateRequestDto parkingOrderUpdateForPayRequestDto = new ParkingOrderPayStatusUpdateRequestDto();
        parkingOrderUpdateForPayRequestDto.setOrderNo(parkingOrder.getOrderNo());
        parkingOrderUpdateForPayRequestDto.setPayedUserId(requestDto.getPayedUserId());
        parkingOrderUpdateForPayRequestDto.setPayTime(requestDto.getSucceedPayTime());
        parkingOrderUpdateForPayRequestDto.setPayStatus(PayStatusEnum.PAY_WAITING.getValue());
        parkingOrderUpdateForPayRequestDto.setActualPayAmount(requestDto.getActualAmount());
        parkingOrderUpdateForPayRequestDto.setPayWay(null);
        parkingOrderUpdateForPayRequestDto.setPayType(null);
        platformParkingOrderService.updateParkingOrder(parkingOrderUpdateForPayRequestDto);

        log.debug("------cancelCompleteSuccessPaymentParkingOrder[订单{}完成支付CANCELING阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

    }
}
