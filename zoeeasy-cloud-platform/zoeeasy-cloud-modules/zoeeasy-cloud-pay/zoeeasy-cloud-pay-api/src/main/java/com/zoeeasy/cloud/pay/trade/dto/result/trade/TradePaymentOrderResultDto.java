package com.zoeeasy.cloud.pay.trade.dto.result.trade;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 平台支付订单表
 *
 * @author walkman
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class TradePaymentOrderResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "customerUserId")
    private Long customerUserId;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "orderNo")
    private String orderNo;

    /**
     * 支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo
     */
    @ApiModelProperty(value = "transactionNo")
    private String transactionNo;

    /**
     * 订单来源
     */
    @ApiModelProperty(value = "orderFrom")
    private String orderFrom;

    /**
     * 交易业务类型 ：消费、充值等
     */
    @ApiModelProperty(value = "bizOrderType")
    private Integer bizOrderType;

    /**
     * 交易业务订单号
     */
    @ApiModelProperty(value = "bizOrderNo")
    private String bizOrderNo;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "orderAmount")
    private Integer orderAmount;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "productName")
    private String productName;

    /**
     * 订单日期
     */
    @ApiModelProperty(value = "orderDate")
    private Date orderDate;

    /**
     * 订单时间
     */
    @ApiModelProperty(value = "orderTime")
    private Date orderTime;

    /**
     * 下单IP(客户端IP,在网关页面获取)
     */
    @ApiModelProperty(value = "orderIp")
    private String orderIp;

    /**
     * 从哪个页面链接过来的(可用于防诈骗)
     */
    @ApiModelProperty(value = "orderRefererUrl")
    private String orderRefererUrl;

    /**
     * 页面回调通知URL
     */
    @ApiModelProperty(value = "returnUrl")
    private String returnUrl;

    /**
     * 后台异步通知URL
     */
    @ApiModelProperty(value = "notifyUrl")
    private String notifyUrl;

    /**
     * 订单有效期(单位分钟)
     */
    @ApiModelProperty(value = "orderPeriod")
    private Integer orderPeriod;

    /**
     * 到期时间
     */
    @ApiModelProperty(value = "expireTime")
    private Date expireTime;

    /**
     * 订单撤销原因
     */
    @ApiModelProperty(value = "cancelReason")
    private String cancelReason;

    /**
     * 订单撤销时间
     */
    @ApiModelProperty(value = "cancelTime")
    private Date cancelTime;

    /**
     * 支付成功时间
     */
    @ApiModelProperty(value = "succeedPayTime")
    private Date succeedPayTime;

    /**
     * 支付通道编号
     */
    @ApiModelProperty(value = "payWay")
    private Integer payWay;

    /**
     * 支付方式类型名称
     */
    @ApiModelProperty(value = "payType")
    private Integer payType;

    /**
     * 资金流入类型
     */
    @ApiModelProperty(value = "fundIntoType")
    private Integer fundIntoType;

    /**
     * 是否退款(0:否,1: 是,默认值为:0)
     */
    @ApiModelProperty(value = "refunded")
    private Boolean refunded;

    /**
     * 退款次数(默认值为:0)
     */
    @ApiModelProperty(value = "refundTimes")
    private Long refundTimes;

    /**
     * 成功退款总金额
     */
    @ApiModelProperty(value = "successRefundAmount")
    private Integer successRefundAmount;

    /**
     * 退款成功时间
     */
    @ApiModelProperty(value = "succeedRefundTime")
    private Date succeedRefundTime;

    /**
     * 状态(参考枚举:PayStatusEnum)
     */
    @ApiModelProperty(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "remark")
    private String remark;

}
