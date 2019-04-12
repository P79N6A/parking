package com.zoeeasy.cloud.order.transaction;

import com.zoeeasy.cloud.order.exception.ParkingBizException;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentAppointmentOrderRequestDto;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentParkingOrderRequestDto;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 停车交易服务
 *
 * @author walkman
 * @date 2018-06-05
 */
public interface ParkingOrderTransactionService {

    /**
     * 停车预约订单支付完成
     *
     * @param transactionContext transactionContext
     * @param requestDto         requestDto
     * @throws ParkingBizException ParkingBizException
     */
    @Compensable
    void completeSuccessPaymentAppointmentOrder(TransactionContext transactionContext, CompletePaymentAppointmentOrderRequestDto requestDto) throws ParkingBizException;


    /**
     * 停车账单支付完成
     *
     * @param transactionContext transactionContext
     * @param requestDto         requestDto
     * @throws ParkingBizException ParkingBizException
     */
    void completeSuccessPaymentParkingOrder(TransactionContext transactionContext, CompletePaymentParkingOrderRequestDto requestDto) throws ParkingBizException;


}
