package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 停车场预约规则信息维护
 *
 * @Date: 2018/03/30
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleUpdateRequestDto", description = "停车场预约规则信息维护")
public class ParkingAppointRuleUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = AppointConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;

    /**
     * 规则列表
     */
    @ApiModelProperty(value = "规则列表", required = true)
    private List<ParkingAppointRuleItemUpdateRequestDto> rules;
}
