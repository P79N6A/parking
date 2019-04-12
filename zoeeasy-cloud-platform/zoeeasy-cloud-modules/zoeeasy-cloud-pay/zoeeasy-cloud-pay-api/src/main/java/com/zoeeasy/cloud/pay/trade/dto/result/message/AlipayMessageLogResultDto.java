package com.zoeeasy.cloud.pay.trade.dto.result.message;

import com.scapegoat.infrastructure.core.dto.result.CreationAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayMessageLogResultDto", description = "")
public class AlipayMessageLogResultDto extends CreationAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 通知时间
     */
    @ApiModelProperty("notifyTime")
    private Date notifyTime;

    /**
     *
     */
    @ApiModelProperty("ip")
    private String ip;

    /**
     * url
     */
    @ApiModelProperty("url")
    private String url;

    /**
     * 通知的类型
     */
    @ApiModelProperty("notifyType")
    private String notifyType;

    /**
     * 通知校验ID
     */
    @ApiModelProperty("notifyId")
    private String notifyId;

    /**
     * 支付宝分配给开发者的应用Id
     */
    @ApiModelProperty("appId")
    private String appId;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty("tradeNo")
    private String tradeNo;

    /**
     * 商户订单号
     */
    @ApiModelProperty("outTradeNo")
    private String outTradeNo;

    /**
     * 商户业务号
     */
    @ApiModelProperty("outBizNo")
    private String outBizNo;

    /**
     * 消息内容
     */
    @ApiModelProperty("content")
    private String content;

    /**
     * 消息处理状态
     */
    @ApiModelProperty("status")
    private Integer status;

    /**
     * 消息处理结果说明
     */
    @ApiModelProperty("result")
    private String result;
}
