package com.zoeeasy.cloud.pay.alipay.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付宝账单结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayDownloadBillResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
    private String payType;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 外部订单号
     */
    private String outTradeNo;

    /**
     * 商品名称
     */
    private String subject;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 实收金额
     */
    private BigDecimal receiptAmount;

    /**
     * 商户的门店编号
     */
    private String storeId;

    /**
     * 商户的门店名称
     */
    private String storeName;

    /**
     * 商户的操作员编号
     */
    private String operatorId;

    /**
     * 商户的终端编号
     */
    private String terminalId;

    /**
     * 支付宝红包
     */
    private BigDecimal zfbPacket;

    /**
     * 集分宝
     */
    private BigDecimal setPointTreasure;

    /**
     * 支付宝优惠
     */
    private BigDecimal zfbDiscount;

    /**
     * 商家优惠
     */
    private BigDecimal storeDiscount;

    /**
     * 券优惠
     */
    private BigDecimal ticketDiscount;

    /**
     * 券名称
     */
    private String ticketName;

    /**
     * 商家红包消费金额
     */
    private BigDecimal storePacketAmount;

    /**
     * 卡消费金额
     */
    private BigDecimal cardPacketAmount;

    /**
     * 服务费
     */
    private BigDecimal serviceCharge;

    /**
     * 分润
     */
    private BigDecimal feeSplitting;

    /**
     * 备注
     */
    private String remark;

    /**
     * 退款批次号/请求号
     */
    private String outRequestNo;

    /**
     * 订单开始时间
     */
    private Date startTime;

    /**
     * 订单结束时间
     */
    private Date endTime;

    /**
     * 买家支付宝账号
     */
    private String buyerLogonId;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String finalTime;

    /**
     * 订单金额
     */
    private String amountTem;

    /**
     * 实收金额
     */
    private String receiptAmountTem;

    /**
     * 支付宝红包
     */
    private String zfbPacketTem;

    /**
     * 集分宝
     */
    private String setPointTreasureTem;

    /**
     * 支付宝优惠
     */
    private String zfbDiscountTem;

    /**
     * 商家优惠
     */
    private String storeDiscountTem;

    /**
     * 券优惠
     */
    private String ticketDiscountTem;

    /**
     * 商家红包消费金额
     */
    private String storePacketAmountTem;

    /**
     * 卡消费金额
     */
    private String cardPacketAmountTem;

    /**
     * 服务费
     */
    private String serviceChargeTem;

    /**
     * 分润
     */
    private String feeSplittingTem;
}
