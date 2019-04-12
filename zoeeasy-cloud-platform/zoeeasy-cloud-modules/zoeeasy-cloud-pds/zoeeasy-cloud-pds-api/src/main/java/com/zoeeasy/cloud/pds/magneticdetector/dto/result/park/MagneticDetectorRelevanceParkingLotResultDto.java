package com.zoeeasy.cloud.pds.magneticdetector.dto.result.park;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询已关联泊位设备参数返回
 *
 * @Date: 2018/10/16
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticDetectorRelevanceParkingLotResultDto", description = "分页查询已关联泊位设备参数返回")
public class MagneticDetectorRelevanceParkingLotResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器编号
     */
    @ApiModelProperty("地磁检测器编号")
    private String code;

    /**
     * (厂商)设备序列号
     */
    @ApiModelProperty(value = "(厂商)设备序列号")
    private String serialNumber;

    /**
     * 厂商
     */
    @ApiModelProperty(value = "厂商")
    private String provider;

    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态")
    private String status;
}
