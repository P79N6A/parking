package com.zoeeasy.cloud.pds.magneticmanager.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import com.zoeeasy.cloud.pds.magneticmanager.cst.MagneticManagerConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 停车设备管理器查询试图
 *
 * @Date: 2018/8/23
 * @author: wh
 */
@Data
@ApiModel(value = "MagneticManagerResultDto", description = "停车设备管理器查询试图")
@EqualsAndHashCode(callSuper = false)
public class MagneticManagerResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场Id
     */
    @ApiModelProperty(value = "停车场Id")
    private Long parkingId;

    /**
     * 地磁管理器编号
     */
    @ApiModelProperty(value = "地磁管理器编号")
    private String code;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    /**
     * 端口号
     */
    @ApiModelProperty(value = "端口号")
    private Integer port;

    /**
     * SIM卡号
     */
    @ApiModelProperty(value = "SIM卡号")
    private String simNo;

    /**
     * 设备版本号
     */
    @ApiModelProperty(value = "设备版本号")
    private String versionNumber;

    /**
     * 设备序列号
     */
    @ApiModelProperty("序列号")
    @Length(min = MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_MIN_LENGTH, max = MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_MAX_LENGTH, message = MagneticDetectorConstant.MAGNETIC_DETECTOR_SERIAL_NUMBER_LENGTH_RANGE)
    private String serialNumber;

    /**
     * 厂商
     */
    @ApiModelProperty("厂商")
    private String provider;

    /**
     * 安装地址
     */
    @ApiModelProperty("安装地址")
    @Length(max = MagneticManagerConstant.MAGNETIC_MANAGER_INSTALL_POSITION_MAX_LENGTH, message = MagneticManagerConstant.MAGNETIC_MANAGER_INSTALL_POSITION_LENGTH_RANGE)
    private String installPosition;

    /**
     * 安装时间
     */
    @ApiModelProperty(value = "安装时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date installationTime;

    /**
     * 注册状态
     */
    @ApiModelProperty(value = "注册状态  0-未注册，1-已注册")
    private String registered;
}
