package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.scapegoat.infrastructure.jackson.convert.deserializer.ToIntegerFenDeserializer;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 添加停车场预约规则请求参数
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRuleAddRequestDto", description = "添加停车场预约规则请求参数")
public class AppointRuleAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约规则编号
     */
    @ApiModelProperty(value = "预约规则编号", required = true)
    @NotBlank(message = AppointConstant.RULE_CODE_NOT_EMPTY)
    private String ruleCode;

    /**
     * 预约规则名称
     */
    @ApiModelProperty(value = "预约规则名称", required = true)
    @NotBlank(message = AppointConstant.RULE_NAME_NOT_EMPTY)
    private String ruleName;

    /**
     * 开放预约开始时间
     */
    @ApiModelProperty(value = "开放预约开始时间", required = true)
    @NotBlank(message = AppointConstant.STARTTIME_NOT_EMPTY)
    private String startTime;

    /**
     * 开放预约结束时间
     */
    @ApiModelProperty(value = "开放预约结束时间", required = true)
    @NotBlank(message = AppointConstant.ENDTIME_NOT_EMPTY)
    private String endTime;

    /**
     * 收费类型
     */
    @ApiModelProperty(value = "收费类型", required = true)
    @NotNull(message = AppointConstant.CHARGE_TYPE_NOT_EMPTY)
    private Integer chargeType;

    /**
     * 收费金额
     */
    @ApiModelProperty(value = "收费金额", required = true)
    @NotNull(message = AppointConstant.CHARGE_PRICE_NOT_EMPTY)
    @Range(min = AppointConstant.PRICE_MIN, message = AppointConstant.PRICE_ILLEGAL)
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    private Integer chargePrice;

    /**
     * 预约服务费用
     */
    @ApiModelProperty(value = "预约服务费用", required = true)
    @NotNull(message = AppointConstant.FEE_NOT_EMPTY)
    @Range(min = AppointConstant.PRICE_MIN, message = AppointConstant.PRICE_ILLEGAL)
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    private Integer fee;

    /**
     * 预定支付时限(分钟)
     */
    @ApiModelProperty(value = "预定支付时限(分钟)", required = true)
    @NotNull(message = AppointConstant.PAYLIMIT_NOT_EMPTY)
    private Integer payLimit;

    /**
     * 已支付可退款时长
     */
    @ApiModelProperty(value = "已支付可退款时长", required = true)
    @NotNull(message = AppointConstant.REFUNDLIMIT_NOT_EMPTY)
    private Integer refundLimit;

    /**
     * 是否退款
     */
    @ApiModelProperty(value = "是否退款", required = true)
    @NotNull(message = AppointConstant.CANREFUND_NOT_EMPTY)
    private Boolean canRefund;

    /**
     * 超时时限(分钟),以预计入场时间开始
     */
    @ApiModelProperty(value = "超时时限(分钟),以预计入场时间开始", required = true)
    @NotNull(message = AppointConstant.OVERTIMELIMIT_NOT_EMPTY)
    private Integer overTimeLimit;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", required = true)
    @NotBlank(message = AppointConstant.DESCRIPTION_NOT_EMPTY)
    private String description;

    /**
     * 预约弹性入场时间
     */
    @ApiModelProperty(value = "预约弹性入场时间", required = true)
    @NotNull(message = AppointConstant.FLEXTIME_NOT_EMPTY)
    private Integer flexTime;
}
