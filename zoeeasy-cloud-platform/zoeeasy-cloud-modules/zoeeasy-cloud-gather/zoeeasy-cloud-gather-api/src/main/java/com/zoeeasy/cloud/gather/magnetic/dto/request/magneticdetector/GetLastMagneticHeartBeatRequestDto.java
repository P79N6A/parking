package com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.gather.magnetic.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 查询当前地磁检测器最近一条心跳数据
 *
 * @Date: 2018/9/21
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "GetLastMagneticHeartBeatRequestDto", description = "查询当前地磁检测器最近一条心跳数据")
public class GetLastMagneticHeartBeatRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 检测器ID
     */
    @ApiModelProperty(value = "检测器ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_ID_NOT_NULL)
    private Long detectorId;

    /**
     * 地磁管理器类型(厂商)
     */
    @ApiModelProperty(value = "地磁管理器类型(厂商)")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_MAGNETIC_PROVIDER_NOT_NULL)
    private Integer provider;
}
