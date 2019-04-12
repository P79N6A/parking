package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取停车场预约规则请求参数
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRuleGetRequestDto", description = "获取停车场预约规则请求参数")
public class AppointRuleGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约规则ID
     */
    @ApiModelProperty(value = "预约规则ID")
    @NotNull(message = AppointConstant.RULE_ID_NOT_EMPTY)
    private Long ruleId;

}
