package com.zoeeasy.cloud.gather.hikvision.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 海康过车记录最后一次校对结束时间
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleCollateTimeResultDto", description = "海康过车记录最后一次校对结束时间")
public class PassingVehicleCollateTimeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "校对结束时间")
    private Date collateEndTime;

}