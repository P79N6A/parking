package com.zoeeasy.cloud.charge.chg.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/12/5 0005
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleTimeViewResultDto", description = "收费规则时间分段视图")
public class ChargeRuleTimeViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 收费规则ID
     */
    @ApiModelProperty("收费规则ID")
    private Long ruleId;

    /**
     * 时间段
     */
    @ApiModelProperty("时间段")
    private Integer timePart;

    /**
     * 本时间段的单价
     */
    @ApiModelProperty("本时间段的单价")
    private Integer price;

}
