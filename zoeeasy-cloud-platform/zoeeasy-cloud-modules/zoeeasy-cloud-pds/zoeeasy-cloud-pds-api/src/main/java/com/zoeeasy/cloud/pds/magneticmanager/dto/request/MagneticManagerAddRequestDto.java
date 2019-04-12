package com.zoeeasy.cloud.pds.magneticmanager.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.IP;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticdetector.validator.ProviderEnumValidator;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import com.zoeeasy.cloud.pds.magneticmanager.validator.LatitudeValidator;
import com.zoeeasy.cloud.pds.magneticmanager.validator.LongitudeValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 添加停车设备管理器请求参数
 *
 * @Date: 2018/8/23
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticManagerAddRequestDto", description = "添加停车设备管理器请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticManagerAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 管理器编号
     */
    @ApiModelProperty(value = "管理器编号")
    @NotBlank(message = MagneticManagerConstant.MAGNETIC_MANAGER_CODE_NOT_NULL)
    @Length(min = MagneticDetectorConstant.MAGNETIC_DETECTOR_CODE_MIN_LENGTH, max = MagneticDetectorConstant.MAGNETIC_DETECTOR_CODE_MAX_LENGTH, message = MagneticDetectorConstant.MAGNETIC_DETECTOR_CODE_LENGTH_RANGE)
    @Pattern(regexp = MagneticManagerConstant.MAGNETIC_MANAGER_CODE_REGULAR, message = MagneticManagerConstant.MAGNETIC_MANAGER_CODE_MESSAGE)
    private String code;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    @NotBlank(message = MagneticManagerConstant.MAGNETIC_MANAGER_IP_ADDRESS_NOT_NULL)
    @IP
    private String ipAddress;

    /**
     * 端口号
     */
    @ApiModelProperty(value = "端口号")
    @NotNull(message = MagneticManagerConstant.MAGNETIC_MANAGER_PORT_NOT_NULL)
    @Range(min = MagneticDetectorConstant.MAGNETIC_DETECTOR_PORT_MIN_LENGTH,
            max = MagneticDetectorConstant.MAGNETIC_DETECTOR_PORT_MAM_LENGTH,
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
     * 设备序列号
     */
    @ApiModelProperty("序列号")
    @Length(min = MagneticManagerConstant.MAGNETIC_MANAGER_SERIAL_NUMBER_MIN_LENGTH, max = MagneticManagerConstant.MAGNETIC_MANAGER_SERIAL_NUMBER_MAX_LENGTH, message = MagneticManagerConstant.MAGNETIC_MANAGER_SERIAL_NUMBER_LENGTH_RANGE)
    @NotBlank(message = MagneticManagerConstant.MAGNETIC_MANAGER_SERIAL_NUMBER_NOT_NULL)
    private String serialNumber;

    /**
     * 厂商
     */
    @ApiModelProperty("厂商")
    @FluentValidate({ProviderEnumValidator.class})
    @NotNull(message = MagneticManagerConstant.MAGNETIC_MANAGER_PROVIDER_NOT_NULL)
    private Integer provider;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    @FluentValidate({LongitudeValidator.class})
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    @FluentValidate({LatitudeValidator.class})
    private Double latitude;

    /**
     * 安装地址
     */
    @ApiModelProperty("安装地址")
    @Length(max = MagneticManagerConstant.MAGNETIC_MANAGER_INSTALL_POSITION_MAX_LENGTH, message = MagneticManagerConstant.MAGNETIC_MANAGER_INSTALL_POSITION_LENGTH_RANGE)
    private String installPosition;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = MagneticManagerConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 心跳间隔（秒）
     */
    @ApiModelProperty(value = "心跳间隔（秒）")
    @Range(min = MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MIN_LENGTH,
            max = MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_MAX_LENGTH,
            message = MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_LENGTH)
    @NotNull(message = MagneticManagerConstant.MAGNETIC_MANAGER_HEARTBEAT_INTEVAL_NOT_NULL)
    private Integer heartBeatInterval;

    /**
     * 安装时间
     */
    @ApiModelProperty(value = "安装时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    @NotNull(message = MagneticManagerConstant.MAGNETIC_MANAGER_INSTALLATION_TIME_NOT_NULL)
    private Date installationTime;
}
