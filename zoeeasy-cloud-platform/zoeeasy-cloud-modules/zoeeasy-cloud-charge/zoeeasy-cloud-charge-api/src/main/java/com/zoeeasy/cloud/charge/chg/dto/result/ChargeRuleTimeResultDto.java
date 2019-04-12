package com.zoeeasy.cloud.charge.chg.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.jackson.convert.serializer.ToBigDecimalYuanSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收费规则时间分段视图
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleTimeResultDto", description = "收费规则时间分段视图")
public class ChargeRuleTimeResultDto extends EntityDto<Long> {

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
    @JsonSerialize(using = ToBigDecimalYuanSerializer.class)
    private Integer price;

}
