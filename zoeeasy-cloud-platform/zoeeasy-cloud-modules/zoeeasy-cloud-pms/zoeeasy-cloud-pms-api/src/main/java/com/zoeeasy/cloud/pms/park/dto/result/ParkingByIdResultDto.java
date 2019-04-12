package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据停车场ID获取区域全称
 * Created by song on 2018/9/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingResultDto", description = "根据停车场ID获取区域全称")
public class ParkingByIdResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    private String areaPath;
}
