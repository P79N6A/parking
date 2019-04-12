package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author walkman
 * @Description: 获取收费规则关联停车信息表请求参数
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeRuleGetRequestDto", description = "获取收费规则关联停车信息表请求参数")
public class ParkingAppointRuleGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = AppointConstant.ID_NOT_EMPTY)
    private Long id;
}
