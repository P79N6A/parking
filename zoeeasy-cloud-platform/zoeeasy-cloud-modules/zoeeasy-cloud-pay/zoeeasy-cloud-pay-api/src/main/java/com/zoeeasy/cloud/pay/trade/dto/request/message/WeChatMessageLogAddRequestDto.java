package com.zoeeasy.cloud.pay.trade.dto.request.message;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 微信通知参数添加
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeChatMessageLogAddRequestDto", description = "微信通知参数添加")
public class WeChatMessageLogAddRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 通知类型(支付OR退款)
     */
    private Integer messageType;

    /**
     *
     */
    private String ip;

    /**
     * url
     */
    private String url;

    /**
     * outRefundNo
     */
    private String outRefundNo;

    /**
     * transactionId
     */
    private String transactionId;

    /**
     * refundId
     */
    private String refundId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 消息处理状态
     */
    private Integer status;

    /**
     * 消息处理结果说明
     */
    private String result;

}
