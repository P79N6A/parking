package com.zoeeasy.cloud.gather.hikvision.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 海康过车记录最后一次同步结束时间
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleSyncTimeResultDto", description = "海康过车记录最后一次同步结束时间")
public class PassingVehicleSyncTimeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "同步结束时间")
    private Date syncEndTime;

}