package com.zhuyitech.parking.ucc.dto.request.recharge;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 获取用户充值记录列表
 *
 * @Date: 2018/3/2
 * @author: yuzhicheng
 */
@ApiModel(value = "UserRechargeRecordListGetRequestDto", description = "获取用户充值记录记录列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRechargeRecordListGetRequestDto extends SessionEntityDto<Long> {

    public static final long serialVersionUID = 1L;

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
    @ApiModelProperty("充值时间")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date rechargeTime;

    /**
     * 充值状态
     */
    @ApiModelProperty("充值状态")
    private Integer rechargeStatus;

    /**
     * 充值成功时间
     */
    @ApiModelProperty("充值成功时间")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date succeedTime;

}
