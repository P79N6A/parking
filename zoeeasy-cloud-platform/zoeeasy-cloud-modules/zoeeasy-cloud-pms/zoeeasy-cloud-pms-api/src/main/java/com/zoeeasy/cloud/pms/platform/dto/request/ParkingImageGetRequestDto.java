package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取停车场图像请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingImageGetRequestDto", description = "获取停车场图像请求参数")
public class ParkingImageGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = PmsConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

}