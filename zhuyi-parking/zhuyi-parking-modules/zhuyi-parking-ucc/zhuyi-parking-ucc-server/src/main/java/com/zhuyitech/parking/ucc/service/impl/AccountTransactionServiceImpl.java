package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.exception.BusinessException;
import com.zhuyitech.parking.common.enums.RechargeStatusEnum;
import com.zhuyitech.parking.pms.dto.request.transaction.CompletePaymentUserPayOrderRequestDto;
import com.zhuyitech.parking.pms.exception.ParkingBizException;
import com.zhuyitech.parking.ucc.domain.UserAsset;
import com.zhuyitech.parking.ucc.domain.UserAssetLog;
import com.zhuyitech.parking.ucc.domain.UserPayOrder;
import com.zhuyitech.parking.ucc.domain.UserRecharge;
import com.zhuyitech.parking.ucc.dto.request.account.*;
import com.zhuyitech.parking.ucc.dto.request.trade.CompletePaymentRechargeOrderRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.ParkingAppointmentOrderPaymentRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.UserParkingOrderPaymentRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.UserPlaceRechargeOrderRequestDto;
import com.zhuyitech.parking.ucc.dto.result.AccountBalanceAvailableCheckResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserAssetInfoResultDto;
import com.zhuyitech.parking.ucc.enums.AssetDirectionEnum;
import com.zhuyitech.parking.ucc.enums.AssetLogStatusEnum;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.exception.AccountBizException;
import com.zhuyitech.parking.ucc.service.UserAssetCrudService;
import com.zhuyitech.parking.ucc.service.UserAssetLogCrudService;
import com.zhuyitech.parking.ucc.service.UserPayOrderCrudService;
import com.zhuyitech.parking.ucc.service.UserRechargeCrudService;
import com.zhuyitech.parking.ucc.service.api.AccountTransactionService;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户资金账户、积分账户交易服务
 *
 * @author walkman
 */
@Service("accountTransactionService")
@Slf4j
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Autowired
    private UserAssetLogCrudService userAssetLogCrudService;

    @Autowired
    private UserAssetCrudService userAssetCrudService;

    @Autowired
    private UserRechargeCrudService userRechargeCrudService;

    @Autowired
    private UserPayOrderCrudService userPayOrderCrudService;

    /**
     * 用户充值下单
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto placeRechargeOrderPayment(UserPlaceRechargeOrderRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            Date now = new Date();
            //用户充值表
            UserRecharge userRecharge = new UserRecharge();
            userRecharge.setOrderUuid(requestDto.getOrderUuid());
            userRecharge.setUserId(userId);
            userRecharge.setCreatorUserId(userId);
            userRecharge.setOrderNo(requestDto.getRechargeOrderNo());
            userRecharge.setPayOrderNo(requestDto.getPayOrderNo());
            userRecharge.setRechargeType(requestDto.getPayWay());
            userRecharge.setRechargeAmount(requestDto.getChargeAmount());
            userRecharge.setRechargeTime(now);
            userRecharge.setRechargeStatus(RechargeStatusEnum.WAITING_PAY.getValue());
            userRechargeCrudService.insert(userRecharge);
            PayWayEnum payWayEnum = PayWayEnum.valueOf(requestDto.getPayWay());

            //用户支付表
            UserPayOrder userPayOrder = sealUserPayOrder(userId, BizTypeEnum.RECHARGE, requestDto.getRechargeOrderNo(),
                    requestDto.getOrderUuid(), requestDto.getPayOrderNo(), requestDto.getChargeAmount(), payWayEnum, now);
            userPayOrderCrudService.insert(userPayOrder);

            resultDto.success();
        } catch (Exception e) {
            log.error("用户充值失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户停车账单支付
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto placeParkingOrderPayment(UserParkingOrderPaymentRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            Date now = new Date();
            PayWayEnum payWayEnum = PayWayEnum.valueOf(requestDto.getPayWay());
            //用户支付表
            UserPayOrder userPayOrder = sealUserPayOrder(userId, BizTypeEnum.PAYMENT, requestDto.getOrderNo(),
                    requestDto.getOrderUuid(), requestDto.getPayOrderNo(), requestDto.getPaymentAmount(), payWayEnum, now);
            userPayOrderCrudService.insert(userPayOrder);

            resultDto.success();
        } catch (Exception e) {
            log.error("用户停车账单支付失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户停车预约订单支付
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto placeAppointmentOrderPayment(ParkingAppointmentOrderPaymentRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            Date now = new Date();
            PayWayEnum payWayEnum = PayWayEnum.valueOf(requestDto.getPayWay());

            //用户支付表
            UserPayOrder userPayOrder = sealUserPayOrder(userId, BizTypeEnum.PAYMENT, requestDto.getOrderNo(),
                    requestDto.getOrderUuid(), requestDto.getPayOrderNo(), requestDto.getPaymentAmount(), payWayEnum, now);
            userPayOrderCrudService.insert(userPayOrder);

            resultDto.success();
        } catch (Exception e) {
            log.error("用户预约订单支付失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;

    }

    /**
     * 账户余额操作
     */
    /**
     * 账户余额加款
     *
     * @param requestDto 加款请求参数
     * @return 加款后的账户信息
     * @throws AccountBizException
     */
    @Override
    public ObjectResultDto<UserAssetInfoResultDto> getUserAssetInfo(AccountBalanceAddRequestDto requestDto) throws AccountBizException {

        ObjectResultDto<UserAssetInfoResultDto> objectResultDto = new ObjectResultDto<>();
        return objectResultDto;
    }

    /**
     * 检查账户余额是否足够
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AccountBalanceAvailableCheckResultDto> checkAccountAvailable(AccountBalanceAvailableCheckRequestDto requestDto) {

        ObjectResultDto<AccountBalanceAvailableCheckResultDto> objectResultDto = new ObjectResultDto<>();

        if (null == requestDto.getPaymentAmount()) {
            return objectResultDto.makeResult(UCenterResultEnum.PAYMENT_AMOUNT_EMPTY.getValue(), UCenterResultEnum.PAYMENT_AMOUNT_EMPTY.getComment());
        }
        Long userId = requestDto.getSessionIdentity().getUserId();
        AccountBalanceAvailableCheckResultDto judgeBalanceResultDto = new AccountBalanceAvailableCheckResultDto();
        UserAsset userAsset = userAssetCrudService.findByUserId(userId);
        if (userAsset.getBalance().compareTo(requestDto.getPaymentAmount()) < 0) {
            judgeBalanceResultDto.setJudgeBalanced(Boolean.FALSE);
        } else {
            judgeBalanceResultDto.setJudgeBalanced(Boolean.TRUE);
        }
        objectResultDto.setData(judgeBalanceResultDto);
        objectResultDto.success();
        return objectResultDto;
    }

    /**
     * 账户余额加款
     *
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void creditToAccount(AccountBalanceAddRequestDto requestDto) throws AccountBizException {
        UserAsset userAsset = userAssetCrudService.findByUserId(requestDto.getUserId());
        if (userAsset == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }

        /** 增加总余额 **/
        userAsset.setBalance(userAsset.getBalance().add(requestDto.getAmount()));

        // 记录账户历史
        UserAssetLog userAssetLog = new UserAssetLog();
        userAssetLog.setUserId(requestDto.getUserId());
        userAssetLog.setAmount(requestDto.getAmount());
        userAssetLog.setBalance(userAsset.getBalance());
        userAssetLog.setTransactionNo(requestDto.getTransactionNo());
        userAssetLog.setBizType(requestDto.getBizType());
        userAssetLog.setBizNo(requestDto.getBizNo());
        userAssetLog.setDirection(AssetDirectionEnum.ADD.getValue());
        userAssetLog.setStatus(AssetLogStatusEnum.CONFORM.getValue());
        userAssetLog.setRemark(requestDto.getRemark());
        this.userAssetLogCrudService.insert(userAssetLog);
        this.userAssetCrudService.updateById(userAsset);
    }

    /**
     * 账户余额加款
     *
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     * @throws AccountBizException AccountBizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmCreditToAccountTcc", cancelMethod = "cancelCreditToAccountTcc")
    public void creditToAccountTcc(TransactionContext transactionContext, AccountBalanceAddRequestDto requestDto) throws AccountBizException {

        log.debug("===>creditToAccountTcc TRYING begin" + ":TransactionNo" + requestDto.getTransactionNo());

        if (requestDto.getUserId() == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        UserAsset account = userAssetCrudService.findByUserId(requestDto.getUserId());
        if (account == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        // 通过请求号唯一来做幂等判断
        UserAssetLog userAssetLog = userAssetLogCrudService.findByTransactionNo(requestDto.getTransactionNo());
        if (userAssetLog == null) {
            //如果账户历史为空,则创建数据,否则,不创建账户历史  防止多次提交
            // 记录账户历史
            userAssetLog = new UserAssetLog();
            userAssetLog.setUserId(requestDto.getUserId());
            userAssetLog.setAmount(requestDto.getAmount());
            userAssetLog.setBalance(account.getBalance().add(requestDto.getAmount()));
            userAssetLog.setTransactionNo(requestDto.getTransactionNo());
            userAssetLog.setBizType(requestDto.getBizType());
            userAssetLog.setBizNo(requestDto.getBizNo());
            userAssetLog.setDirection(AssetDirectionEnum.ADD.getValue());
            // 状态为TRYING（业务表设计上要有对应的状态为配合TCC的状态）
            userAssetLog.setStatus(AssetLogStatusEnum.TRYING.getValue());
            userAssetLog.setRemark(requestDto.getRemark());
            this.userAssetLogCrudService.insert(userAssetLog);
        } else if (AssetLogStatusEnum.CANCEL.getValue().equals(userAssetLog.getStatus())) {

            //如果是取消的,有可能是之前的业务出现异常问题而取消,那么重试阶段,再将状态更新为TYING状态,而不是重新创建一条
            log.debug("之前因为业务问题取消后,又重试的{}", userAssetLog.getBizNo());
            userAssetLog.setStatus(AssetLogStatusEnum.TRYING.getValue());
            this.userAssetLogCrudService.updateById(userAssetLog);
        }
        log.debug("===>creditToAccountTcc TRYING end" + ":TransactionNo" + requestDto.getTransactionNo());
    }

    /**
     * 资金账户加款确认阶段
     *
     * @param transactionContext
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmCreditToAccountTcc(TransactionContext transactionContext, AccountBalanceAddRequestDto requestDto) {

        log.debug("===>confirmCreditToAccountTcc begin" + ":TransactionNo" + requestDto.getTransactionNo());

        UserAssetLog userAssetLog = this.userAssetLogCrudService.findByTransactionNo(requestDto.getTransactionNo());

        // 幂等判断
        if (userAssetLog == null || !AssetLogStatusEnum.TRYING.getValue().equals(userAssetLog.getStatus())) {
            //如果账户历史为空,或者状态为非TRYING中的,就不执行确认操作
            return;
        }

        // 设置状态为CONFIRM
        userAssetLog.setStatus(AssetLogStatusEnum.CONFORM.getValue());
        this.userAssetLogCrudService.updateById(userAssetLog);

        UserAsset userAsset = userAssetCrudService.findByUserId(requestDto.getUserId());
        if (userAsset == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        /** 设置余额的值 **/
        userAsset.setBalance(userAsset.getBalance().add(requestDto.getAmount()));

        this.userAssetCrudService.updateById(userAsset);

        log.debug("===>confirmCreditToAccountTcc end" + ":TransactionNo" + requestDto.getTransactionNo());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelCreditToAccountTcc(TransactionContext transactionContext, AccountBalanceAddRequestDto requestDto) {

        log.debug("===>cancelCreditToAccountTcc begin" + ":TransactionNo" + requestDto.getTransactionNo());

        UserAssetLog userAssetLog = userAssetLogCrudService.findByTransactionNo(requestDto.getTransactionNo());

        // 幂等判断
        if (userAssetLog == null || !AssetLogStatusEnum.TRYING.getValue().equals(userAssetLog.getStatus())) {
            //如果账户历史为空,或者状态为非TRYING中的,就不执行取消操作
            return;
        }
        //设置状态为取消状态
        userAssetLog.setStatus(AssetLogStatusEnum.CANCEL.getValue());

        this.userAssetLogCrudService.updateById(userAssetLog);

        log.debug("===>cancelCreditToAccountTcc end" + ":TransactionNo" + requestDto.getTransactionNo());
    }

    /**
     * 账户余额减款
     *
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    public void debitToAccount(AccountBalanceSubtractRequestDto requestDto) throws AccountBizException {

        UserAsset userAsset = userAssetCrudService.findByUserId(requestDto.getUserId());
        if (userAsset == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        // 获取可用余额
        BigDecimal availableBalance = userAsset.getBalance();

        if (availableBalance.compareTo(requestDto.getAmount()) < 0) {
            throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
        }

        /** 减少总余额 **/
        userAsset.setBalance(userAsset.getBalance().subtract(requestDto.getAmount()));

        // 记录账户历史
        UserAssetLog userAssetLog = new UserAssetLog();
        userAssetLog.setUserId(requestDto.getUserId());
        userAssetLog.setAmount(requestDto.getAmount());
        userAssetLog.setBalance(userAsset.getBalance());
        userAssetLog.setTransactionNo(requestDto.getTransactionNo());
        userAssetLog.setBizType(requestDto.getBizType());
        userAssetLog.setBizNo(requestDto.getBizNo());
        userAssetLog.setDirection(AssetDirectionEnum.SUB.getValue());
        userAssetLog.setStatus(AssetLogStatusEnum.CONFORM.getValue());
        userAssetLog.setRemark(requestDto.getRemark());
        this.userAssetLogCrudService.insert(userAssetLog);
        this.userAssetCrudService.updateById(userAsset);
    }

    /**
     * 账户余额减款
     *
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     * @throws AccountBizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmDebitToAccountTcc", cancelMethod = "cancelDebitToAccountTcc")
    public void debitToAccountTcc(TransactionContext transactionContext, AccountBalanceSubtractRequestDto requestDto) throws AccountBizException {

        log.debug("===>creditToAccountTcc TRYING begin");

        if (requestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw AccountBizException.ACCOUNT_SUB_AMOUNT_INVALID;
        }

        UserAsset userAsset = userAssetCrudService.findByUserId(requestDto.getUserId());
        if (userAsset == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        // 获取可用余额
        BigDecimal availableBalance = userAsset.getBalance();

        if (availableBalance.compareTo(requestDto.getAmount()) < 0) {
            throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
        }

        // 通过请求号唯一来做幂等判断
        UserAssetLog userAssetLog = userAssetLogCrudService.findByBizNo(requestDto.getBizNo());
        if (userAssetLog == null) {
            //如果账户历史为空,则创建数据,否则,不创建账户历史  防止多次提交
            // 记录账户历史
            userAssetLog = new UserAssetLog();
            userAssetLog.setUserId(requestDto.getUserId());
            userAssetLog.setAmount(requestDto.getAmount());
            userAssetLog.setBalance(userAsset.getBalance().subtract(requestDto.getAmount()));
            userAssetLog.setTransactionNo(requestDto.getTransactionNo());
            userAssetLog.setBizType(requestDto.getBizType());
            userAssetLog.setBizNo(requestDto.getBizNo());
            userAssetLog.setDirection(AssetDirectionEnum.SUB.getValue());
            // 状态为TRYING（业务表设计上要有对应的状态为配合TCC的状态）
            userAssetLog.setStatus(AssetLogStatusEnum.TRYING.getValue());
            userAssetLog.setRemark(requestDto.getRemark());
            this.userAssetLogCrudService.insert(userAssetLog);
        } else if (AssetLogStatusEnum.CANCEL.getValue().equals(userAssetLog.getStatus())) {

            //如果是取消的,有可能是之前的业务出现异常问题而取消,那么重试阶段,再将状态更新为TYING状态,而不是重新创建一条
            log.info("之前因为业务问题取消后,又重试的{}", userAssetLog.getBizNo());
            userAssetLog.setStatus(AssetLogStatusEnum.TRYING.getValue());
            this.userAssetLogCrudService.updateById(userAssetLog);
        }
        log.debug("===>creditToAccountTcc TRYING end");
    }

    /**
     * 账户余额减款确认阶段
     *
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void confirmDebitToAccountTcc(TransactionContext transactionContext, AccountBalanceSubtractRequestDto requestDto) {

        log.debug("===>confirmDebitToAccountTcc begin");

        UserAssetLog userAssetLog = this.userAssetLogCrudService.findByBizNo(requestDto.getBizNo());

        // 幂等判断
        if (userAssetLog == null || !(AssetLogStatusEnum.TRYING.getValue().equals(userAssetLog.getStatus()))) {
            //如果账户历史为空,或者状态为非TRYING中的,就不执行确认操作
            return;
        }

        // 设置状态为CONFIRM
        userAssetLog.setStatus(AssetLogStatusEnum.CONFORM.getValue());
        this.userAssetLogCrudService.updateById(userAssetLog);

        UserAsset userAsset = userAssetCrudService.findByUserId(requestDto.getUserId());
        if (userAsset == null) {
            throw AccountBizException.ACCOUNT_NOT_EXIT;
        }
        // 获取可用余额
        BigDecimal availableBalance = userAsset.getBalance();
        if (availableBalance.compareTo(requestDto.getAmount()) < 0) {
            throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
        }
        /** 设置余额的值 **/
        userAsset.setBalance(userAsset.getBalance().subtract(requestDto.getAmount()));

        this.userAssetCrudService.updateById(userAsset);

        log.debug("===>confirmDebitToAccountTcc end");
    }

    /**
     * 账户余额减款取消阶段
     *
     * @param transactionContext TCC事务上下文
     * @param requestDto         加款请求参数
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelDebitToAccountTcc(TransactionContext transactionContext, AccountBalanceSubtractRequestDto requestDto) {

        log.debug("===>cancelDebitToAccountTcc begin");

        UserAssetLog userAssetLog = userAssetLogCrudService.findByBizNo(requestDto.getBizNo());

        // 幂等判断
        if (userAssetLog == null || !AssetLogStatusEnum.TRYING.getValue().equals(userAssetLog.getStatus())) {
            //如果账户历史为空,或者状态为非TRYING中的,就不执行确认操作
            return;
        }
        //设置状态为取消状态
        userAssetLog.setStatus(AssetLogStatusEnum.CANCEL.getValue());

        this.userAssetLogCrudService.updateById(userAssetLog);

        log.debug("===>cancelDebitToAccountTcc end");
    }

    /**
     * 积分余额加款
     *
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    public void creditToPointAccount(AccountPointAddRequestDto requestDto) throws AccountBizException {

    }

    /**
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    public void creditToPointAccountTcc(TransactionContext transactionContext, AccountPointAddRequestDto requestDto) throws AccountBizException {

    }

    /**
     * 积分余额加款
     *
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    public void debitToPointAccount(AccountPointSubtractRequestDto requestDto) throws AccountBizException {

    }

    /**
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    public void debitToPointAccountTcc(TransactionContext transactionContext, AccountPointSubtractRequestDto requestDto) throws AccountBizException {

    }


    /**
     * 封装用户支付订单信息
     *
     * @param userId     userId
     * @param bizType    bizType
     * @param bizOrderNo bizOrderNo
     * @param orderUuid  orderUuid
     * @param payOrderNo payOrderNo
     * @param payAmount  payAmount
     * @param payWay     payWay
     * @param payTime    payTime
     * @return UserPayOrder
     */
    private UserPayOrder sealUserPayOrder(Long userId, BizTypeEnum bizType, String bizOrderNo, String orderUuid, String payOrderNo,
                                          BigDecimal payAmount, PayWayEnum payWay, Date payTime) {

        //用户支付表
        UserPayOrder userPayOrder = new UserPayOrder();
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        userPayOrder.setUserId(userId);
        if (bizType == null) {
            throw new BusinessException("业务类型不能为空");
        }
        userPayOrder.setBizOrderType(bizType.getValue());
        if (StringUtils.isEmpty(bizOrderNo)) {
            throw new BusinessException("业务流水号不能为空");
        }
        userPayOrder.setBizOrderNo(bizOrderNo);
        userPayOrder.setOrderUuid(orderUuid);
        if (StringUtils.isEmpty(payOrderNo)) {
            throw new BusinessException("支付订单号不能为空");
        }
        userPayOrder.setOrderNo(payOrderNo);
        if (payWay == null) {
            throw new BusinessException("支付方式不能为空");
        }
        userPayOrder.setPayWay(payWay.getValue());
        if (payAmount == null || payAmount.doubleValue() <= 0) {
            throw new BusinessException("订单金额错误");
        }
        userPayOrder.setPayAmount(payAmount);
        userPayOrder.setPayStatus(PayStatusEnum.CREATED.getValue());
        userPayOrder.setPayTime(payTime);
        return userPayOrder;
    }

    /**
     * 用户充值支付完成
     *
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmCompleteSuccessPaymentRechargeOrder", cancelMethod = "cancelCompleteSuccessPaymentRechargeOrder")
    public void completeSuccessPaymentRechargeOrder(TransactionContext transactionContext, CompletePaymentRechargeOrderRequestDto requestDto) throws AccountBizException {

        log.info("------completeSuccessPaymentRechargeOrder[订单{}完成支付TRYING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        //充值订单
        UserRecharge userRecharge = userRechargeCrudService.findByOrderNo(requestDto.getOrderNo());
        if (userRecharge == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }
        userRecharge.setRechargeRealAmount(requestDto.getActualAmount());
        userRecharge.setRechargeStatus(RechargeStatusEnum.TRADE_PROCESSING.getValue());
        userRecharge.setSucceedTime(requestDto.getSucceedPayTime());
        userRechargeCrudService.updateById(userRecharge);

        //用户支付订单
        UserPayOrder userPayOrder = userPayOrderCrudService.findByOrderNo(requestDto.getPayOrderNo());
        if (userPayOrder == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }
        userPayOrder.setPayStatus(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue());
        userPayOrder.setPayAmountActual(requestDto.getActualAmount());
        userPayOrder.setSucceedPayTime(requestDto.getSucceedPayTime());
        userPayOrder.setTransactionNo(requestDto.getTransactionNo());
        userPayOrderCrudService.updateById(userPayOrder);

        log.info("------completeSuccessPaymentRechargeOrder[订单{}完成支付TRYING阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    /**
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    public void confirmCompleteSuccessPaymentRechargeOrder(TransactionContext transactionContext, CompletePaymentRechargeOrderRequestDto requestDto) throws AccountBizException {

        log.debug("------confirmCompleteSuccessPaymentRechargeOrder[订单{}完成支付CONFIRM阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        //充值订单
        UserRecharge userRecharge = userRechargeCrudService.findByOrderNo(requestDto.getOrderNo());
        if (userRecharge == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }
        // 幂等判断
        if (!RechargeStatusEnum.TRADE_PROCESSING.getValue().equals(userRecharge.getRechargeStatus())) {
            //如果充值状态非为支付处理中就不执行确认操作
            return;
        }
        userRecharge.setRechargeRealAmount(requestDto.getActualAmount());
        userRecharge.setRechargeStatus(RechargeStatusEnum.TRADE_SUCCESS.getValue());
        userRecharge.setSucceedTime(requestDto.getSucceedPayTime());
        userRechargeCrudService.updateById(userRecharge);

        //用户支付订单
        UserPayOrder userPayOrder = userPayOrderCrudService.findByOrderNo(requestDto.getPayOrderNo());
        if (userPayOrder == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }
        userPayOrder.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
        userPayOrder.setPayAmountActual(requestDto.getActualAmount());
        userPayOrder.setSucceedPayTime(requestDto.getSucceedPayTime());
        userPayOrder.setTransactionNo(requestDto.getTransactionNo());
        userPayOrderCrudService.updateById(userPayOrder);

        log.debug("------confirmCompleteSuccessPaymentRechargeOrder[订单{}完成支付CONFIRM阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    /**
     * @param transactionContext
     * @param requestDto
     * @throws AccountBizException
     */
    @Override
    public void cancelCompleteSuccessPaymentRechargeOrder(TransactionContext transactionContext, CompletePaymentRechargeOrderRequestDto requestDto) throws AccountBizException {

        log.debug("------cancelCompleteSuccessPaymentRechargeOrder[订单{}完成支付CANCELING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        UserRecharge userRecharge = userRechargeCrudService.findByOrderNo(requestDto.getOrderNo());
//       幂等判断
        if (RechargeStatusEnum.TRADE_SUCCESS.getValue().equals(userRecharge.getRechargeStatus())
                || !RechargeStatusEnum.TRADE_PROCESSING.getValue().equals(userRecharge.getRechargeStatus())) {
            log.info("订单状态：{}，不能执行取消动作", userRecharge.getRechargeStatus());
            return;
        }
        userRecharge.setRechargeStatus(RechargeStatusEnum.WAITING_PAY.getValue());
        userRechargeCrudService.updateById(userRecharge);

        UserPayOrder userPayOrder = userPayOrderCrudService.findByOrderNo(requestDto.getPayOrderNo());
        if (userPayOrder == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }
        userPayOrder.setPayStatus(PayStatusEnum.PAY_WAITING.getValue());
        userPayOrder.setTransactionNo(requestDto.getTransactionNo());
        userPayOrderCrudService.updateById(userPayOrder);

        log.debug("------cancelCompleteSuccessPaymentRechargeOrder[订单{}完成支付CANCELING阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Compensable(confirmMethod = "confirmCompleteSuccessPaymentUserPayOrder", cancelMethod = "cancelCompleteSuccessPaymentUserPayOrder")
    public void completeSuccessPaymentUserPayOrder(TransactionContext transactionContext, CompletePaymentUserPayOrderRequestDto requestDto) throws ParkingBizException {
        log.debug("------completeSuccessPaymentUserPayOrder[订单{}完成支付维护userPayOrder表TRYING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        UserPayOrder userPayOrder = userPayOrderCrudService.findByOrderNo(requestDto.getPayOrderNo());
        if (userPayOrder == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }
        if (PayStatusEnum.PAY_SUCCESS.getValue().equals(userPayOrder.getPayStatus())) {
            log.info("订单状态：{}，不能执行userPayOrder表维护动作", userPayOrder.getPayStatus());
            return;
        }
        userPayOrder.setPayAmountActual(requestDto.getActualAmount());
        userPayOrder.setPayStatus(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue());
        userPayOrder.setSucceedPayTime(requestDto.getSucceedPayTime());
        userPayOrder.setTransactionNo(requestDto.getTransactionNo());
        userPayOrderCrudService.updateById(userPayOrder);

        log.debug("------completeSuccessPaymentUserPayOrder[订单{}完成支付维护userPayOrder表TRYING阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmCompleteSuccessPaymentUserPayOrder(TransactionContext transactionContext, CompletePaymentUserPayOrderRequestDto requestDto) throws ParkingBizException {
        log.debug("------confirmCompleteSuccessPaymentUserPayOrder[订单{}完成支付维护userPayOrder表CONFIRM阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        UserPayOrder userPayOrder = userPayOrderCrudService.findByOrderNo(requestDto.getPayOrderNo());
        if (userPayOrder == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }

        //userPayOrder表维护
        userPayOrder.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
        userPayOrder.setPayAmountActual(requestDto.getActualAmount());
        userPayOrder.setSucceedPayTime(requestDto.getSucceedPayTime());
        userPayOrder.setTransactionNo(requestDto.getTransactionNo());
        userPayOrderCrudService.updateById(userPayOrder);

        log.debug("------confirmCompleteSuccessPaymentUserPayOrder[订单{}完成支付维护userPayOrder表CONFIRM阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelCompleteSuccessPaymentUserPayOrder(TransactionContext transactionContext, CompletePaymentUserPayOrderRequestDto requestDto) throws ParkingBizException {
        log.debug("------cancelCompleteSuccessPaymentUserPayOrder[订单{}完成支付维护userPayOrder表CANCELING阶段开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        UserPayOrder userPayOrder = userPayOrderCrudService.findByOrderNo(requestDto.getPayOrderNo());
        if (userPayOrder == null) {
            throw new AccountBizException(UCenterResultEnum.RECORD_EMPTY.getValue(), UCenterResultEnum.RECORD_EMPTY.getComment());
        }

        //userPayOrder表维护
        userPayOrder.setPayStatus(PayStatusEnum.PAY_WAITING.getValue());
        userPayOrderCrudService.updateById(userPayOrder);

        log.debug("------cancelCompleteSuccessPaymentUserPayOrder[订单{}完成支付维护userPayOrder表CANCELING阶段结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

}
