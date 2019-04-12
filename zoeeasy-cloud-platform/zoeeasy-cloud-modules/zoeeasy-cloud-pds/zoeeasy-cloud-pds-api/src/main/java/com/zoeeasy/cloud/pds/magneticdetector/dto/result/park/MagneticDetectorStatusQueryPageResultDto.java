package com.zoeeasy.cloud.pds.magneticdetector.dto.result.park;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页获取地磁检测器状态参数返回
 *
 * @Date: 2018/10/12
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticDetectorStatusQueryPageResultDto", description = "分页获取地磁检测器参数返回")
public class MagneticDetectorStatusQueryPageResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 地磁检测器序列号
     */
    @ApiModelProperty(value = "地磁检测器序列号")
    private String serialNumber;

    /**
     * 地磁管理器序列号
     */
    @ApiModelProperty(value = "地磁管理器序列号")
    private String managerSerialNumber;

    /**
     * 电量（%）
     */
    @ApiModelProperty(value = "电量（%）")
    private String electricity;

    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态")
    private String status;

    /**
     * 厂商
     */
    @ApiModelProperty(value = "厂商")
    private String provider;
}
