package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleGetByIdRequestDto", description = "根据id获取收费规则列表请求参数")
public class ChargeRuleGetByRuleIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 规则ID
     */
    @ApiModelProperty(value = "规则ID", required = true)
    @NotNull(message = "规则id不能为空")
    private Long ruleId;

    /**
     * 租户Id
     */
    @ApiModelProperty(value = "租户Id", required = true)
    @NotNull(message = "租户Id不能为空")
    private Long tenantId;

}
