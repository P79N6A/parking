package com.zhuyitech.parking.ucc.dto.request.pay;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 获取支付记录列表请求参数
 *
 * @Date: 2018/3/13
 * @author: yuzhicheng
 */
@ApiModel(value = "UserPayOrderListGetRequestDto", description = "获取支付记录列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPayOrderListGetRequestDto extends SessionEntityDto<Long> {

    public static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("userId")
    private Long userId;

    /**
     * 订单UUID
     */
    @ApiModelProperty("orderUuid")
    private String orderUuid;

    /**
     * 系统支付订单号
     */
    @ApiModelProperty("orderNo")
    private String orderNo;

    /**
     * 业务订单号（ 分别为充值订单号、缴费订单号）
     */
    @ApiModelProperty("bizOrderNo")
    private String bizOrderNo;

    /**
     * 订单类型（1.充值，2，付款）
     */
    @ApiModelProperty("orderType")
    private Integer orderType;

    /**
     * 支付方式（1. 支付宝，2，微信）
     */
    @ApiModelProperty("payType")
    private Integer payType;

    /**
     * 支付状态
     */
    @ApiModelProperty("payStatus")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty("payTime")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date payTime;

    /**
     * 支付成功时间
     */
    @ApiModelProperty("succeedPayTime")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date succeedPayTime;

}
