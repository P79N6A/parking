package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户充值记录视图
 *
 * @Date: 2018/3/2
 * @author: yuzhicheng
 */
@ApiModel(value = "UserRechargeRecordResultDto", description = "用户充值记录视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRechargeRecordResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单UUID
     */
    @ApiModelProperty("订单UUID")
    private String orderUuid;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty("支付订单号")
    private String payOrderNo;

    /**
     * 充值金额
     */
    @ApiModelProperty("充值金额")
    private BigDecimal rechargeAmount;

    /**
     * 充值到账金额
     */
    @ApiModelProperty("充值到账金额")
    private BigDecimal rechargeRealAmount;

    /**
     * 充值方式
     */
    @ApiModelProperty("充值方式")
    private Integer rechargeType;

    /**
     * 支付渠道
     */
    @ApiModelProperty("支付渠道")
    private Integer rechargeChannel;

    /**
     * 充值时间
     */
    @ApiModelProperty("支付渠道")
    private Date rechargeTime;

    /**
     * 充值成功时间
     */
    @ApiModelProperty("充值成功时间")
    private Date succeedTime;

    /**
     * 充值状态
     */
    @ApiModelProperty("充值状态")
    private Integer rechargeStatus;

}
