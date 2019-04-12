package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 分页查询未关联泊位设备请求参数
 *
 * @Date: 2018/10/16
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto", description = "分页查询未关联泊位设备请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto extends SignedSessionPagedRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器编号
     */
    @ApiModelProperty("地磁检测器编号")
    private String code;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    @NotNull(message = MagneticDetectorConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;
}
