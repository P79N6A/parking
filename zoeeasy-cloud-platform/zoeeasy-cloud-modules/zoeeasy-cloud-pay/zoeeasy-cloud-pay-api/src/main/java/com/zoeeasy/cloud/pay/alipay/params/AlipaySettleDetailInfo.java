package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 结算详细信息
 *
 * @author walkman
 */
@Getter
@Setter
public class AlipaySettleDetailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 必填	64	结算收款方的账户类型。
     * cardSerialNo：结算收款方的银行卡编号;
     * userId：表示是支付宝账号对应的支付宝唯一用户号;
     * loginName：表示是支付宝登录号；	cardSerialNo
     */
    private String transInType;

    /**
     * 必填	64	结算收款方。当结算收款方类型是cardSerialNo时，本参数为用户在支付宝绑定的卡编号；结算收款方类型是userId时，本参数为用户的支付宝账号对应的支付宝唯一用户号，
     * 以2088开头的纯16位数字；当结算收款方类型是loginName时，本参数为用户的支付宝登录号
     */
    private String transIn;

    /**
     * 可选	64	结算汇总维度，按照这个维度汇总成批次结算，由商户指定。
     * 目前需要和结算收款方账户类型为cardSerialNo配合使用
     */
    private String summaryDimension;

    /**
     * 可选	64	结算主体标识。当结算主体类型为SecondMerchant时，为二级商户的SecondMerchantID；当结算主体类型为Store时，为门店的外标。
     */
    private String settleEntityId;

    /**
     * 结算主体类型。
     * 二级商户:SecondMerchant;商户或者直连商户门店:Store	SecondMerchant、Store
     */
    private String settleEntityType;

    /**
     * 填	9	结算的金额，单位为元。目前必须和交易金额相同
     */
    private BigDecimal amount;

}
