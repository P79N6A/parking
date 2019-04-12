package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取预约规则列表请求参数
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRuleGetListRequestDto", description = "获取预约规则列表请求参数")
public class AppointRuleGetListRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约规则名称
     */
    @ApiModelProperty(value = "预约规则名称")
    private String ruleName;
}
