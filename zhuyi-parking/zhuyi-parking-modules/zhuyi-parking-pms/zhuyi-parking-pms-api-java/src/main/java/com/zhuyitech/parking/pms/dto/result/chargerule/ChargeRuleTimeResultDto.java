package com.zhuyitech.parking.pms.dto.result.chargerule;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


/**
 * 收费规则时间分段视图
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@ApiModel(value = "ChargeRuleTimeResultDto", description = "收费规则时间分段视图")
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeRuleTimeResultDto extends AuditedEntityDto<Long> {

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
    private BigDecimal price;

}
