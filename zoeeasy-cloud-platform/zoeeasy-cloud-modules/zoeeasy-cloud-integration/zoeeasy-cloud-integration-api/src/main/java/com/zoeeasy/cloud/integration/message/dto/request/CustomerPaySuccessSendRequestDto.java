package com.zoeeasy.cloud.integration.message.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;

/**
 * 用户支付成功通知消息发送请求参数
 *
 * @author walkman
 */
@ApiModel(value = "MessageSendUserPayOrderRequestDto", description = "用户支付成功通知消息发送请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerPaySuccessSendRequestDto extends BaseDto {

    /**
     * 支付用户ID
     */
    private Long customerUserId;

    /**
     * 业务订单ID
     */
    private Long bizOrderId;

    /**
     * 业务订单类型
     */
    private Integer bizOrderType;

    /**
     * 业务订单号
     */
    private String bizOrderNo;

    /**
     * 交易订单号
     */
    private String transactionNo;

    /**
     * 支付订单号
     */
    private String payOrderNo;

    /**
     * 支付成功时间
     */
    private Date succeedPayTime;

    /**
     * 实际支付金额(分)
     */
    private Integer actualAmount;

    /**
     * 支付方式
     */
    private Integer payWay;

    /**
     * 支付类型
     */
    private Integer payType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附加数据
     */
    private Map<String, Object> attachment;
}
