package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 用户资产信息信息视图
 *
 * @author walkman
 */
@ApiModel(value = "UserAssetInfoResultDto", description = "用户资产信息信息视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAssetInfoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "userId")
    private Long userId;

    /**
     * 账户余额
     */
    @ApiModelProperty(value = "balance")
    private BigDecimal balance;

    /**
     * 用户积分
     */
    @ApiModelProperty(value = "point")
    private BigDecimal point;

    /**
     * 可用优惠券数量
     */
    @ApiModelProperty(value = "couponCount")
    private Integer couponCount;

    /**
     * 可用优惠券金额
     */
    @ApiModelProperty(value = "couponBalance")
    private BigDecimal couponBalance;

    /**
     * 红包数量
     */
    @ApiModelProperty(value = "packetCount")
    private Integer packetCount;

    /**
     * 红包金额
     */
    @ApiModelProperty(value = "packetBalance")
    private BigDecimal packetBalance;

    /**
     * 未支付订单数
     */
    @ApiModelProperty(value = "unPaidCount")
    private Integer unPaidCount;

    /**
     * 未支付金额
     */
    @ApiModelProperty(value = "unPaidBalance")
    private BigDecimal unPaidBalance;

    /**
     * 违章停车数
     */
    @ApiModelProperty(value = "violationCount")
    private Integer violationCount;

    /**
     * 违章停车金额
     */
    @ApiModelProperty(value = "violationBalance")
    private BigDecimal violationBalance;

}