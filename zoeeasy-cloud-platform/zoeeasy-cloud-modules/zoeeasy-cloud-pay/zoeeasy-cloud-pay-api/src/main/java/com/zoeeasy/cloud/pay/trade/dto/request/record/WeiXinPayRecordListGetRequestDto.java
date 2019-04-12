package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 获取微信支付记录列表
 *
 * @Date: 2018/09/12
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeiXinPayRecordListGetRequestDto", description = "获取微信支付记录列表")
public class WeiXinPayRecordListGetRequestDto extends SessionEntityDto<Long> {

    public static final long serialVersionUID = 1L;

    /**
     * 商户订单号
     */
    @ApiModelProperty("商户订单号")
    private String outTradeNo;

    /**
     * 微信支付订单号
     */
    @ApiModelProperty("微信支付订单号")
    private String transactionId;

    /**
     * 交易状态
     */
    @ApiModelProperty("交易状态")
    private Integer tradeState;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String body;

    /**
     * 商品详情
     */
    @ApiModelProperty("商品详情")
    private String detail;

    /**
     * 总金额
     */
    @ApiModelProperty("总金额")
    private BigDecimal totalFee;

    /**
     * 交易起始时间
     */
    @ApiModelProperty("交易起始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date timeStart;

    /**
     * 交易完成时间
     */
    @ApiModelProperty("交易完成时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date timeEnd;
}
