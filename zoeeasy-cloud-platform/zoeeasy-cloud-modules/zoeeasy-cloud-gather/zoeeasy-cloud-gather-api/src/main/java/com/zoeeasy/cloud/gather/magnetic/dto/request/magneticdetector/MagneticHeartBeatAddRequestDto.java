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
 * 添加设备心跳
 *
 * @Date: 2018/9/21
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "MagneticHeartBeatAddRequestDto", description = "添加设备心跳")
public class MagneticHeartBeatAddRequestDto extends SignedSessionRequestDto {

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
    @NotNull(message =MagneticDetectorConstant.MAGNETIC_DETECTOR_PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;

    /**
     * 检测器ID
     */
    @ApiModelProperty(value = "检测器ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_DETECTOR_ID_NOT_NULL)
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
     * 指令编码
     */
    @ApiModelProperty("指令编码")
    @Length(min = MagneticDetectorConstant.MAGNETIC_DETECTOR_COMMAND_CODE_MIN_LENGTH, max = MagneticDetectorConstant.MAGNETIC_DETECTOR_COMMAND_CODE_MAX_LENGTH,
            message = MagneticDetectorConstant.MAGNETIC_DETECTOR_COMMAND_CODE_LENGTH_RANGE)
    private String commandCode;

    /**
     * 心跳时间
     */
    @ApiModelProperty("心跳时间")
    @NotNull(message =MagneticDetectorConstant.MAGNETIC_DETECTOR_BEAT_TIME_NOT_NULL)
    private Date beatTime;

    /**
     * 设备状态异常编码
     */
    @ApiModelProperty("设备状态异常码")
    private Integer errorCode;

    /**
     * 设备电量(单位%)
     */
    @ApiModelProperty("battery")
    @Range(min = MagneticDetectorConstant.MAGNETIC_DETECTOR_BATTERY_MIN_LENGTH, max = MagneticDetectorConstant.MAGNETIC_DETECTOR_BATTERY_MAX_LENGTH,
            message = MagneticDetectorConstant.MAGNETIC_DETECTOR_BATTERY_LENGTH_RANGE)
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
