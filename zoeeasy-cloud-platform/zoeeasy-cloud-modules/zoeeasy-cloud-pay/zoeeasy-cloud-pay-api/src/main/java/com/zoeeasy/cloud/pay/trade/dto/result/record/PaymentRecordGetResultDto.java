package com.zoeeasy.cloud.pay.trade.dto.result.record;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 根据订单查询
 *
 * @Date: 2018/06/27
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentRecordGetResultDto", description = "根据订单查询")
public class PaymentRecordGetResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 交易订单号
     */
    private String orderNo;

    /**
     * 支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo
     */
    private String transactionNo;

    /**
     * 订单来源
     */
    private String orderFrom;

    /**
     * 交易业务类型 ：消费、充值等
     */
    private Integer bizOrderType;

    /**
     * 交易业务订单号
     */
    private String bizOrderNo;

    /**
     * 订单金额
     */
    private Integer orderAmount;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 付款人ID
     */
    private Long payerUserId;

    /**
     * 付款人名称
     */
    private String payerUsername;

    /**
     * 付款方支付金额
     */
    private Integer payerPayAmount;

    /**
     * 付款方手续费
     */
    private String payerFee;

    /**
     * 付款方账户类型(参考账户类型枚举:AccountTypeEnum)
     */
    private String payerAccountType;

    /**
     * 收款人ID
     */
    private Long receiverUserId;

    /**
     * 收款人名称
     */
    private String receiverUsername;

    /**
     * 收款方支付金额
     */
    private BigDecimal receiverPayAmount;

    /**
     * 收款方手续费
     */
    private BigDecimal receiverFee;

    /**
     * 收款方账户类型(参考账户类型枚举:AccountTypeEnum)
     */
    private String receiverAccountType;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 订单时间
     */
    private Date orderTime;

    /**
     * 下单IP(客户端IP,在网关页面获取)
     */
    private String orderIp;

    /**
     * 从哪个页面链接过来的(可用于防诈骗)
     */
    private String orderRefererUrl;

    /**
     * 页面回调通知URL
     */
    private String returnUrl;

    /**
     * 后台异步通知URL
     */
    private String notifyUrl;

    /**
     * 平台收入
     */
    private BigDecimal platformIncome;

    /**
     * 平台成本
     */
    private BigDecimal platformCost;

    /**
     * 平台利润
     */
    private BigDecimal platformProfit;

    /**
     * 费率
     */
    private BigDecimal feeRate;

    /**
     * 支付方式(根据PayWayEnum)
     */
    private Integer payWay;

    /**
     * 支付类型(根据PayTypeEnum)
     */
    private Integer payType;

    /**
     * 支付方式类型编号
     */
    private String payTypeCode;

    /**
     * 支付方式类型名称
     */
    private String payTypeName;

    /**
     * 支付成功时间
     */
    private Date succeedPayTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 资金流入类型
     */
    private String fundIntoType;

    /**
     * 是否退款(0:否,1: 是,默认值为:0)
     */
    private Boolean refunded;

    /**
     * 退款次数(默认值为:0)
     */
    private Long refundTimes;

    /**
     * 成功退款总金额
     */
    private BigDecimal successRefundAmount;

    /**
     * 退款成功时间
     */
    private Date succeedRefundTime;

    /**
     * 状态(参考枚举:PayStatusEnum)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户ID
     */
    private Long customerUserId;
}
