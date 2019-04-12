package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.IP;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 更新设备请求参数
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorUpdateRequestDto", description = "更新设备请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorUpdateRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 安装位置
     */
    @ApiModelProperty(value = "安装位置")
    @Length(max = MagneticDetectorConstant.MAGNETIC_DETECTOR_INSTALL_POSITION_MAX_LENGTH,
            message = MagneticDetectorConstant.MAGNETIC_DETECTOR_INSTALL_POSITION_LENGTH_RANGE)
    private String installPosition;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    @IP
    private String ipAddress;

    /**
     * 端口号
     */
    @ApiModelProperty(value = "端口号")
    @Range(max = MagneticDetectorConstant.MAGNETIC_DETECTOR_PORT_MAM_LENGTH,
            message = MagneticDetectorConstant.MAGNETIC_DETECTOR_PORT_LENGTH)
    private Integer port;

    /**
     * SIM卡号
     */
    @ApiModelProperty(value = "SIM卡号")
    @Length(max = MagneticManagerConstant.MAGNETIC_MANAGER_SIM_NO_MAX_LENGTH, message = MagneticManagerConstant.MAGNETIC_MANAGER_SIM_NO_LENGTH_RANGE)
    @Pattern(regexp = MagneticDetectorConstant.SPECIAL_CHARACTER_PATTERN, message = MagneticManagerConstant.MAGNETIC_MANAGER_SIM_NO_LENGTH_RANGE)
    private String simNo;

    /**
     * 设备版本号
     */
    @ApiModelProperty(value = "设备版本号")
    @Length(max = MagneticDetectorConstant.MAGNETIC_DETECTOR_VERSION_NUMBER_MAX_LENGTH, message = MagneticDetectorConstant.MAGNETIC_DETECTOR_VERSION_NUMBER_LENGTH_RANGE)
    private String versionNumber;

    /**
     * 心跳间隔（秒）
     */
    @ApiModelProperty(value = "心跳间隔（秒）")
    @Range(min = MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MIN_LENGTH,
            max = MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MAX_LENGTH,
            message = MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_LENGTH)
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_HEARTBEAT_INTEVAL_NOT_NULL)
    private Integer heartBeatInterval;

    /**
     * 安装时间
     */
    @ApiModelProperty(value = "安装时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_INSTALLATION_TIME_NOT_NULL)
    private Date installationTime;
}
