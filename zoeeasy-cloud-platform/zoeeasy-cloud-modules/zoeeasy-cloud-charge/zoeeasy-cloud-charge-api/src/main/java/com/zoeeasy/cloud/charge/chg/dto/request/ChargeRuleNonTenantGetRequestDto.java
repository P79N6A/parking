package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 分页获取收费规则(无租户)请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleNonTenantGetRequestDto", description = "分页获取收费规则(无租户)请求参数")
public class ChargeRuleNonTenantGetRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    @NotNull(message = ChargeConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

}
