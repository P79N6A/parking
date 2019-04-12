package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 可预约停车场数量
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointmentParkingCountResultDto", description = "可预约停车场数量")
public class AppointmentParkingCountResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 可预约停车场数量
     */
    @ApiModelProperty(value = "可预约停车场数量")
    private Integer count;

}
