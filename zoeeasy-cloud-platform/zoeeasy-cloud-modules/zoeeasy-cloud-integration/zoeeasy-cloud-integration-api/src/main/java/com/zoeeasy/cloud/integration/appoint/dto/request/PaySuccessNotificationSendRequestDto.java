package com.zoeeasy.cloud.integration.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 发送账单支付成功推送通知与消息请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaySuccessNotificationSendRequestDto", description = "发送账单支付成功推送通知与消息请求参数")
public class PaySuccessNotificationSendRequestDto extends SessionDto {

    public static final long serialVersionUID = 1L;

    /**
     * startTime
     */
    @ApiModelProperty(value = "startTime")
    private Date startTime;

    /**
     * parkingName
     */
    @ApiModelProperty(value = "parkingName")
    private String parkingName;

    /**
     * orderId
     */
    @ApiModelProperty(value = "orderId")
    private Long orderId;

    /**
     * orderNo
     */
    @ApiModelProperty(value = "orderNo")
    private String orderNo;

    /**
     * payedUserId
     */
    @ApiModelProperty(value = "payedUserId")
    private Long payedUserId;

    /**
     * payableAmount
     */
    @ApiModelProperty(value = "payableAmount")
    private BigDecimal payableAmount;
}
