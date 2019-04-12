package com.zhuyitech.parking.ucc.service.api;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.pms.dto.request.transaction.CompletePaymentUserPayOrderRequestDto;
import com.zhuyitech.parking.pms.exception.ParkingBizException;
import com.zhuyitech.parking.ucc.dto.request.account.*;
import com.zhuyitech.parking.ucc.dto.request.trade.CompletePaymentRechargeOrderRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.ParkingAppointmentOrderPaymentRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.UserParkingOrderPaymentRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.UserPlaceRechargeOrderRequestDto;
import com.zhuyitech.parking.ucc.dto.result.AccountBalanceAvailableCheckResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserAssetInfoResultDto;
import com.zhuyitech.parking.ucc.exception.AccountBizException;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;


/**
 * 用户资金账户、积分账户交易服务
 *
 * @author walkman
 */
public interface AccountTransactionService {

    /**
     * 用户充值下单
     *
     * @param requestDto
     * @return
     */
    ResultDto placeRechargeOrderPayment(UserPlaceRechargeOrderRequestDto requestDto);

    /**
     * 用户停车账单支付
     *
     * @param requestDto
     * @return
     */
    ResultDto placeParkingOrderPayment(UserParkingOrderPaymentRequestDto requestDto);

    /**
     * 用户停车预约订单支付
     *
     * @param requestDto
     * @return
     */
    ResultDto placeAppointmentOrderPayment(ParkingAppointmentOrderPaymentRequestDto requestDto);

    /**
     * 账户余额操作
     */
    /**
     * 获取账户资产信息
     *
     * @param requestDto 加款请求参数
     * @return 账户资产信息
     * @throws AccountBizException AccountBizException
     */
    ObjectResultDto<UserAssetInfoResultDto> getUserAssetInfo(AccountBalanceAddRequestDto requestDto) throws AccountBizException;

    /**
     * 检查账户余额是否足够
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AccountBalanceAvailableCheckResultDto> checkAccountAvailable(AccountBalanceAvailableCheckRequestDto requestDto);

    /**
     * 账户余额加款
     *
     * @param requestDto requestDto
     * @throws AccountBizException AccountBizException
     */
    void creditToAccount(AccountBalanceAddRequestDto requestDto) throws AccountBizException;

    /**
     * 账户余额加款
     *
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     * @throws AccountBizException AccountBizException
     */
    @Compensable
    void creditToAccountTcc(TransactionContext transactionContext, AccountBalanceAddRequestDto requestDto) throws AccountBizException;

    /**
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     * @throws AccountBizException AccountBizException
     */
    void confirmCreditToAccountTcc(TransactionContext transactionContext, AccountBalanceAddRequestDto requestDto) throws AccountBizException;

    /**
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     * @throws AccountBizException AccountBizException
     */
    void cancelCreditToAccountTcc(TransactionContext transactionContext, AccountBalanceAddRequestDto requestDto) throws AccountBizException;

    /**
     * 账户余额减款
     *
     * @param requestDto 加款请求参数
     * @throws AccountBizException AccountBizException
     */
    void debitToAccount(AccountBalanceSubtractRequestDto requestDto) throws AccountBizException;

    /**
     * 账户余额减款
     *
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     * @throws AccountBizException AccountBizException
     */
    void debitToAccountTcc(TransactionContext transactionContext, AccountBalanceSubtractRequestDto requestDto) throws AccountBizException;

    /**
     * 积分余额加款
     *
     * @param requestDto
     * @throws AccountBizException
     */
    void creditToPointAccount(AccountPointAddRequestDto requestDto) throws AccountBizException;

    /**
     * @param transactionContext transactionContext
     * @param requestDto         requestDto
     * @throws AccountBizException AccountBizException
     */
    void creditToPointAccountTcc(TransactionContext transactionContext, AccountPointAddRequestDto requestDto) throws AccountBizException;

    /**
     * 积分余额加款
     *
     * @param requestDto requestDto
     * @throws AccountBizException AccountBizException
     */
    void debitToPointAccount(AccountPointSubtractRequestDto requestDto) throws AccountBizException;

    /**
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    void debitToPointAccountTcc(TransactionContext transactionContext, AccountPointSubtractRequestDto requestDto) throws AccountBizException;

    /**
     * 用户充值支付完成
     *
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    @Compensable
    void completeSuccessPaymentRechargeOrder(TransactionContext transactionContext, CompletePaymentRechargeOrderRequestDto requestDto) throws AccountBizException;

    /**
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    void confirmCompleteSuccessPaymentRechargeOrder(TransactionContext transactionContext, CompletePaymentRechargeOrderRequestDto requestDto) throws AccountBizException;

    /**
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    void cancelCompleteSuccessPaymentRechargeOrder(TransactionContext transactionContext, CompletePaymentRechargeOrderRequestDto requestDto) throws AccountBizException;

    /**
     * 停车预约订单支付完成处理userPayOrder
     *
     * @param transactionContext transactionContext
     * @param requestDto         requestDto
     * @throws ParkingBizException ParkingBizException
     */
    @Compensable
    void completeSuccessPaymentUserPayOrder(TransactionContext transactionContext, CompletePaymentUserPayOrderRequestDto requestDto) throws ParkingBizException;

    /**
     * @param transactionContext
     * @param requestDto
     * @throws ParkingBizException
     */
    void confirmCompleteSuccessPaymentUserPayOrder(TransactionContext transactionContext, CompletePaymentUserPayOrderRequestDto requestDto) throws ParkingBizException;

    /**
     * @param transactionContext
     * @param requestDto
     * @throws ParkingBizException
     */
    void cancelCompleteSuccessPaymentUserPayOrder(TransactionContext transactionContext, CompletePaymentUserPayOrderRequestDto requestDto) throws ParkingBizException;

}
