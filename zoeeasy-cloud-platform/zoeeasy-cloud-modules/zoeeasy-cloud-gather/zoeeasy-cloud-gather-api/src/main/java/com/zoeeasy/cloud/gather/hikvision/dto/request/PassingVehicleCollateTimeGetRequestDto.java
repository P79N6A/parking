package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 海康过车记录最后一次校对结束时间
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleCollateTimeGetRequestDto", description = "海康过车记录最后一次同步结束时间")
public class PassingVehicleCollateTimeGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;
}