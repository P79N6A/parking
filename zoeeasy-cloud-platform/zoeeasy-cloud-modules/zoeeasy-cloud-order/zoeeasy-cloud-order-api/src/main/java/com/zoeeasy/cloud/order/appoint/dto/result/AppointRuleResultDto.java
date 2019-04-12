package com.zoeeasy.cloud.order.appoint.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

/**
 * 预约规则视图模型
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRuleResultDto", description = "预约规则视图模型")
public class AppointRuleResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 预约规则编号
     */
    @ApiModelProperty(value = "预约规则编号")
    private String ruleCode;

    /**
     * 预约规则名称
     */
    @ApiModelProperty(value = "预约规则名称")
    private String ruleName;

    /**
     * 开放预约开始时间
     */
    @ApiModelProperty(value = "开放预约开始时间")
    private String startTime;

    /**
     * 开放预约结束时间
     */
    @ApiModelProperty(value = "开放预约结束时间")
    private String endTime;

    /**
     * 收费类型
     */
    @ApiModelProperty(value = "收费类型")
    private Integer chargeType;

    /**
     * 收费金额
     */
    @ApiModelProperty(value = "收费金额")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#0.00")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal chargePrice;

    /**
     * 预约服务费用
     */
    @ApiModelProperty(value = "预约服务费用")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#0.00")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal fee;

    /**
     * 预定支付时限(分钟)
     */
    @ApiModelProperty(value = "预定支付时限(分钟)")
    private Integer payLimit;

    /**
     * 已支付可退款时长
     */
    @ApiModelProperty(value = "已支付可退款时长")
    private Integer refundLimit;

    /**
     * 是否退款
     */
    @ApiModelProperty(value = "是否退款")
    private Boolean canRefund;

    /**
     * 超时时限(分钟),以预计入场时间开始
     */
    @ApiModelProperty(value = "超时时限(分钟),以预计入场时间开始")
    private Integer overTimeLimit;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 预约弹性入场时间
     */
    @ApiModelProperty(value = "预约弹性入场时间")
    private Integer flexTime;

    /**
     * 是否关联
     */
    @ApiModelProperty(value = "是否关联")
    private Boolean used;

    /**
     * 单位时长(分钟)收取金额
     */
    @ApiModelProperty(value = "单位时长(分钟)收取金额")
    private BigDecimal unitPrice;

    /**
     * 单次收取金额
     */
    @ApiModelProperty(value = "单次收取金额")
    private BigDecimal countAppointPrice;
}
