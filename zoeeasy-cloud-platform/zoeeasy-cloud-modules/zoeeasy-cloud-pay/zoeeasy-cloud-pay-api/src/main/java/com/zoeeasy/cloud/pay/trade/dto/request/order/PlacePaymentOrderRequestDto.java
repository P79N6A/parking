package com.zoeeasy.cloud.pay.trade.dto.request.order;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 支付订单下单请求参数
 *
 * @author walkman
 * @date 2018-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PlacePaymentOrderRequestDto", description = "支付订单下单请求参数")
public class PlacePaymentOrderRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付用户ID
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_USER_NOT_NULL)
    private Long payerUserId;

    /**
     * 业务类型
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_BIZ_TYPE_NOT_NULL)
    private Integer bizOrderType;

    /**
     *
     */
    @NotEmpty(message = PaymentConst.PAYMENT_PAY_BIZ_ORDER_NOT_NULL)
    private String bizOrderNo;

    /**
     * 支付方式
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_WAY_NOT_NULL)
    private Integer payWay;

    /**
     * 支付类型
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_TYPE_NOT_NULL)
    private Integer payType;

    /**
     * 订单金额
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_AMOUNT_NOT_NULL)
    private Integer orderAmount;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 下单日期
     */
    private Date orderDate;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 下单IP
     */
    private String orderIp;

    /**
     * 订单有效期(分钟)
     */
    private Integer orderPeriod;

    /**
     * 页面通知地址
     */
    private String returnUrl;

    /**
     * 支付备注
     */
    private String remark;

    /**
     * openId
     */
    private String openId;

    private String buyerId;

    /**
     * parkingId
     */
    private Long parkingId;

    /**
     * talentId
     */
    private Long tenantId;
}
