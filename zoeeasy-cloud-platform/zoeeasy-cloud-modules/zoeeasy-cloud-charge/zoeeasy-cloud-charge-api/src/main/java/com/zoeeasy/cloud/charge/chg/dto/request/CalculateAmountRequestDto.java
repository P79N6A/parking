package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CalculateAmountRequestDto", description = "计算收费请求参数")
public class CalculateAmountRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间", required = true)
    @NotNull(message = "停车开始时间不能为空")
    private Date startTime;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id", required = true)
    @NotNull(message = "租户id不能为空")
    private Long tenantId;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间", required = true)
    @NotNull(message = "停车结束时间不能为空")
    private Date endTime;

    /**
     * 该时间段内的收费规则
     */
    @ApiModelProperty(value = "该时间段内的收费规则", required = true)
    private List<ChargeRuleInfoViewResultDto> parkingChargeRules;
}
