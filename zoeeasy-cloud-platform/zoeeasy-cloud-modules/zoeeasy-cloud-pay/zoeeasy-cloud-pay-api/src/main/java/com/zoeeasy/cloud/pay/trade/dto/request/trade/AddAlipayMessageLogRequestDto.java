package com.zoeeasy.cloud.pay.trade.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 添加支付宝回调日志
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddAlipayMessageLogRequestDto")
public class AddAlipayMessageLogRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 通知时间
     */
    private Date notifyTime;

    /**
     * ip
     */
    private String ip;

    /**
     * url
     */
    private String url;

    /**
     * 通知的类型
     */
    private String notifyType;

    /**
     * 通知校验ID
     */
    private String notifyId;

    /**
     * 支付宝分配给开发者的应用Id
     */
    private String appId;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户业务号
     */
    private String outBizNo;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息处理状态
     */
    private Integer status;

    /**
     * 消息处理结果说明
     */
    private String result;
}
