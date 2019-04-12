package com.zoeeasy.cloud.pay.trade.dto.request.alipay;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 支付宝支付请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayPayPrepareRequestDto", description = "支付宝支付请求参数")
public class AlipayPayPrepareRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     */
    @ApiModelProperty(value = "body")
    private String body;

    /**
     * 商品的标题/交易标题/订单标题/订单关键字等
     */
    @ApiModelProperty(value = "subject", required = true)
    private String subject;

    /**
     * 商户网站唯一订单号
     */
    @ApiModelProperty(value = "outTradeNo", required = true)
    private String outTradeNo;

    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     * 注：若为空，则默认为15d。
     */
    @ApiModelProperty(value = "timeoutExpress", required = true, notes = "最晚付款时间")
    private String timeoutExpress;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "totalAmount", required = true, notes = " 类型 1前缀 2首字母")
    private BigDecimal totalAmount;

    /**
     * 商品主类型：0—虚拟类商品，1—实物类商品
     * 注：虚拟类商品不支持使用花呗渠道
     */
    @ApiModelProperty(value = "商品主类型", notes = "商品主类型：0—虚拟类商品，1—实物类商品")
    private Integer goodsType;

    /**
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
     */
    @ApiModelProperty(value = "公用回传参数", notes = "公用回传参数")
    private String passbackParams;

    /**
     * 优惠参数
     */
    @ApiModelProperty(value = "优惠参数", notes = "优惠参数")
    private String promoParams;

    /**
     * 业务扩展参数，详见下面的“业务扩展参数说明”
     */
    @ApiModelProperty(value = "业务扩展参数", notes = "业务扩展参数")
    private String extendParams;

    /**
     * 可用渠道，用户只能在指定渠道范围内支付
     * 当有多个渠道时用“,”分隔
     * 注：与disable_pay_channels互斥
     */
    @ApiModelProperty(value = "可用渠道", notes = "可用渠道")
    private String enablePayChannels;

    /**
     * 禁用渠道，用户不可用指定渠道支付
     * 当有多个渠道时用“,”分隔
     * 注：与enable_pay_channels互斥
     */
    @ApiModelProperty(value = "禁用渠道", notes = "禁用渠道")
    private String disablePayChannels;

    /**
     * 商户门店编号。该参数用于请求参数中以区分各门店，非必传项。
     */
    @ApiModelProperty(value = "storeId", notes = "storeId")
    private String storeId;
}
