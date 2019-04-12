package com.zoeeasy.cloud.charge.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeRuleUpdateRequestDto", description = "停车场收费规则关联请求参数")
public class ParkingChargeRuleUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ChargeConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 规则列表
     */
    @ApiModelProperty(value = "规则列表", required = true)
    private List<ParkingChargeRuleItemUpdateRequestDto> rules;
}
