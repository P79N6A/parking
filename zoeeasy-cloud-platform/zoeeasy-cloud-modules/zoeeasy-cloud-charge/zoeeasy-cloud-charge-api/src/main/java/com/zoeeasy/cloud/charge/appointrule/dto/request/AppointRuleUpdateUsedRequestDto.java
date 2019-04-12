package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 修改预约规则关联状态
 *
 * @author zwq
 * @date 2018/10/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRuleUpdateUsedRequestDto", description = "修改预约规则关联状态")
public class AppointRuleUpdateUsedRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约规则ID
     */
    @ApiModelProperty(value = "预约规则ID", required = true)
    @NotNull(message = AppointConstant.RULE_ID_NOT_EMPTY)
    private Long ruleId;

    /**
     * 是否关联
     */
    @ApiModelProperty(value = "是否关联")
    @NotNull(message = AppointConstant.USED_NOT_EMPTY)
    private Boolean used;
}
