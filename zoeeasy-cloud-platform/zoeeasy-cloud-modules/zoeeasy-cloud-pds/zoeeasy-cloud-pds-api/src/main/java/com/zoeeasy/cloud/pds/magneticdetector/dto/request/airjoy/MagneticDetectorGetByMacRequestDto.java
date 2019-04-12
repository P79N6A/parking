package com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 根据厂商和设备序列号查询对应的地磁检测器请求参数
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
@Data
@ApiModel(value = "MagneticDetectorGetByMacRequestDto",description = "根据厂商和设备ID查询对应的地磁检测器请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorGetByMacRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 地磁管理器类型(厂商)
     */
    @ApiModelProperty("地磁管理器类型(厂商)")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_PROVIDER_NOT_NULL)
    private Integer provider;


    /**
     * (厂商)设备序列号
     */
    @ApiModelProperty("(厂商)设备序列号")
    @NotNull(message =MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_LENGTH_RANGE)
    private String serialNumber;

}

