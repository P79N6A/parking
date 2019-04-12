package com.zoeeasy.cloud.pay.trade.dto.result.record;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信支付订单视图
 *
 * @author walkman
 * @Date: 2018/2/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WxPayOrderResultDto", description = "微信支付订单视图")
public class WxPayOrderResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 商户订单号
     */
    @ApiModelProperty(value = "outTradeNo")
    private String outTradeNo;

    /**
     * 微信支付订单号
     */
    @ApiModelProperty(value = "transactionId")
    private String transactionId;

    /**
     * 预支付交易会话标识
     */
    @ApiModelProperty(value = "prepayId")
    private String prepayId;

    /**
     * 交易状态
     */
    @ApiModelProperty(value = "tradeState")
    private Integer tradeState;

    /**
     * 交易状态描述
     */
    @ApiModelProperty(value = "tradeStateDesc")
    private String tradeStateDesc;

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "body")
    private String body;

    /**
     * 商品详情
     */
    @ApiModelProperty(value = "detail")
    private String detail;

    /**
     * 附加数据
     */
    @ApiModelProperty(value = "attach")
    private String attach;

    /**
     * 交易类型
     */
    @ApiModelProperty(value = "tradeType")
    private String tradeType;

    /**
     * 指定支付方式
     */
    @ApiModelProperty(value = "limitPay")
    private String limitPay;

    /**
     * 场景信息
     */
    @ApiModelProperty(value = "sceneInfo")
    private String sceneInfo;

    /**
     * 设备号
     */
    @ApiModelProperty(value = "deviceInfo")
    private String deviceInfo;

    /**
     * 货币类型
     */
    @ApiModelProperty(value = "feeType")
    private String feeType;

    /**
     * 总金额
     */
    @ApiModelProperty(value = "totalFee")
    private BigDecimal totalFee;

    /**
     * 现金支付金额
     */
    @ApiModelProperty(value = "cashFee")
    private BigDecimal cashFee;

    /**
     * 现金支付货币类型
     */
    @ApiModelProperty(value = "cashFeeType")
    private String cashFeeType;

    /**
     * 代金券使用数量
     */
    @ApiModelProperty(value = "couponFee")
    private Integer couponFee;

    /**
     * 应结订单金额
     */
    @ApiModelProperty(value = "settlementTotalFee")
    private BigDecimal settlementTotalFee;

    /**
     * 终端IP
     */
    @ApiModelProperty(value = "spbillCreateIp")
    private String spbillCreateIp;

    /**
     * 交易起始时间
     */
    @ApiModelProperty(value = "timeStart")
    private Date timeStart;

    /**
     * 支付完成时间
     */
    @ApiModelProperty(value = "timeEnd")
    private Date timeEnd;

    /**
     * 订单优惠标记
     */
    @ApiModelProperty(value = "goodsTag")
    private String goodsTag;

    /**
     * 用户标识
     */
    @ApiModelProperty(value = "openid")
    private String openid;

    /**
     * 是否关注公众账号
     */
    @ApiModelProperty(value = "subscribed")
    private Boolean subscribed;

    /**
     * 付款银行
     */
    @ApiModelProperty(value = "bankType")
    private String bankType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "remark")
    private String remark;
}
