package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author zwq
 * @Description: 查询停车场预约规则请求参数
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleQueryRequestDto", description = "查询停车场预约规则请求参数")
public class ParkingAppointRuleQueryRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "parkingId")
    @NotNull(message = AppointConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;
}
