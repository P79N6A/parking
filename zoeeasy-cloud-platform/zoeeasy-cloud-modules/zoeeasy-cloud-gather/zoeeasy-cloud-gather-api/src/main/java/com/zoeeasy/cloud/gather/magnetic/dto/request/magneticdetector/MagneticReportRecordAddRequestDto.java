package com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.magnetic.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 艾佳地磁检测记录添加请求参数
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MagneticReportRecordAddRequestDto", description = "艾佳地磁检测记录添加请求参数")
public class MagneticReportRecordAddRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_TENANT_ID_NOT_NULL)
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty("泊位ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;

    /**
     * 检测器ID
     */
    @ApiModelProperty("检测器ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_ID_NOT_NULL)
    private Long detectorId;

    /**
     * 厂商
     */
    @ApiModelProperty("厂商")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_PROVIDER_NOT_NULL)
    private Integer provider;

    /**
     * 序列号
     */
    @ApiModelProperty("序列号")
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
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 泊位业务变更原因(1-车辆到达, 2-车辆离开)
     */
    @ApiModelProperty("泊位业务变更原因(1-车辆到达, 2-车辆离开)")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_CHANGE_TYPE_NOT_NULL)
    private Integer changeType;

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
