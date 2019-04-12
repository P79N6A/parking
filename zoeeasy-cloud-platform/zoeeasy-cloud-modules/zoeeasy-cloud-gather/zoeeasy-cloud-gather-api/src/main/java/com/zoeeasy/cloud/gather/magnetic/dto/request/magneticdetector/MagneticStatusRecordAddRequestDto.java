package com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.gather.magnetic.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 新增地磁状态变更请求参数
 *
 * @author lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticStatusRecordAddRequestDto", description = "新增地磁状态变更请求参数")
public class MagneticStatusRecordAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_TENANT_ID_NOT_NULL)
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;

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

    /**
     * (厂商)设备序列号
     */
    @ApiModelProperty("(厂商)设备序列号")
    @NotEmpty(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_NOT_NULL)
    @Length(min = MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_MIN_LENGTH, max = MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_MAX_LENGTH,
            message = MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_LENGTH_RANGE)
    private String serialNumber;

    /**
     * 变更时间，地磁检测到车位状态变更上传数据的时间
     */
    @ApiModelProperty("变更时间，地磁检测到车位状态变更上传数据的时间")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_CHANGE_TIME_NOT_NULL)
    private Date changeTime;

    /**
     * 泊位业务变更原因(1-车辆到达, 2-车辆离开)
     */
    @ApiModelProperty(value = "泊位业务变更原因(1-车辆到达, 2-车辆离开)")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_CHANGE_TYPE_NOT_NULL)
    private Integer changeType;

    /**
     * 信号强度
     */
    @ApiModelProperty(value = "信号强度")
    private Integer rssi;

    /**
     * 车位状态产生时长
     */
    @ApiModelProperty("车位状态产生时长")
    private Integer passTime;

    /**
     * 车位状态序号
     */
    @ApiModelProperty(value = "车位状态序号")
    private Long sequence;

    /**
     * 设备电量(单位%)
     */
    @ApiModelProperty("battery")
    @Range(min = MagneticDetectorConstant.MAGNETIC_DETECTOR_BATTERY_MIN_LENGTH, max = MagneticDetectorConstant.MAGNETIC_DETECTOR_BATTERY_MAX_LENGTH, message = MagneticDetectorConstant.MAGNETIC_DETECTOR_BATTERY_LENGTH_RANGE)
    private Integer battery;

    /**
     * 占用状态
     */
    @ApiModelProperty("占用状态")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_OCCUPY_STATUS_NOT_NULL)
    private Integer occupyStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date creationTime;
}
