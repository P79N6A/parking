package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 分页查询已关联管理器设备
 *
 * @Date: 2018/10/16
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorRelevanceQueryPageRequestDto", description = "分页查询已关联设备")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorRelevanceQueryPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 管理器id
     */
    @ApiModelProperty(value="管理器id")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_MANAGER_ID_NOT_NULL)
    private Long managerId;

    /**
     * 停车场Id
     */
    @ApiModelProperty(value="停车场Id")
    @NotNull(message = MagneticDetectorConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;
}
