package com.zoeeasy.cloud.charge.appointrule.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 预约规则视图模型
 *
 * @author zwq
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleDetailViewResultDto", description = "预约规则视图模型")
public class ParkingAppointRuleDetailViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 预定单位时长(分钟)
     */
    @ApiModelProperty(value = "预定单位时长(分钟)")
    private Integer unitAppointLength;

    /**
     * 预定最大时长(分钟)
     */
    @ApiModelProperty(value = "预定最大时长(分钟)")
    private Integer maxAppointLength;

    /**
     * 单位时长(分钟)收取金额
     */
    @ApiModelProperty(value = "单位时长(分钟)收取金额")
    private Integer unitPrice;

    /**
     * 预定最大收费金额
     */
    @ApiModelProperty(value = "预定最大收费金额")
    private Integer maxAppointAmount;

    /**
     * 单次收取金额
     */
    @ApiModelProperty(value = "单次收取金额")
    private Integer countAppointPrice;

    /**
     * 预定收取手续费
     */
    @ApiModelProperty(value = "预定收取手续费")
    private Integer fee;

    /**
     * 免收手续费时长
     */
    @ApiModelProperty(value = "免收手续费时长")
    private Integer feeFreeLength;

    /**
     * 预定支付时限(分钟)
     */
    @ApiModelProperty(value = "预定支付时限(分钟)")
    private Integer payLimit;

    /**
     * 预定取消时限(分钟),以下单时间开始
     */
    @ApiModelProperty(value = "预定取消时限(分钟),以下单时间开始")
    private Integer cancelLimit;

    /**
     * 预定取消单位时长(分钟)
     */
    @ApiModelProperty(value = "预定取消单位时长(分钟)")
    private Integer unitCancelLength;

    /**
     * 单位时长(分钟)收取金额
     */
    @ApiModelProperty(value = "单位时长(分钟)收取金额")
    private Integer unitCancelPrice;

    /**
     * 预定取消最大收费金额
     */
    @ApiModelProperty(value = "预定取消最大收费金额")
    private Integer maxCancelAmount;

    /**
     * 单次取消收取金额
     */
    @ApiModelProperty(value = "单次取消收取金额")
    private Integer countCancelPrice;

    /**
     * 超时时限(分钟),以预计入场时间开始
     */
    @ApiModelProperty(value = "超时时限(分钟),以预计入场时间开始")
    private Integer overTimeLimit;

    /**
     * 超时是否取消
     */
    @ApiModelProperty(value = "超时是否取消")
    private Boolean overTimeCancel;

    /**
     * 预定取消单位时长(分钟)
     */
    @ApiModelProperty(value = "预定取消单位时长(分钟)")
    private Integer unitOvertimeCancelLength;

    /**
     * 单位时长(分钟)收取金额
     */
    @ApiModelProperty(value = "单位时长(分钟)收取金额")
    private Integer unitOvertimeCancelPrice;

    /**
     * 预定取消最大收费金额
     */
    @ApiModelProperty(value = "预定取消最大收费金额")
    private Integer maxOvertimeCancelAmount;

    /**
     * 单次取消收取金额
     */
    @ApiModelProperty(value = "单次取消收取金额")
    private Integer countOvertimeCancelPrice;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
}
