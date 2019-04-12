package com.zoeeasy.cloud.charge.chg.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 收费规则条数请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeRuleTotalRequestDto", description = "收费规则条数请求参数")
public class ChargeRuleTotalRequestDto extends SignedRequestDto {

    /**
     * 停车场id
     */
    @ApiModelProperty("parkingId")
    @NotNull(message = ChargeConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

}
