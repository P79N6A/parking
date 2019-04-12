package com.zoeeasy.cloud.pds.magneticdetector.dto.result.park;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 设备视图模型
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorResultDto", description = "MagneticDetectorResultDto")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器Id
     */
    @ApiModelProperty(value = "地磁检测器Id")
    private Long id;

    /**
     * 地磁编号
     */
    @ApiModelProperty(value = "地磁编号")
    private String code;

    /**
     * (厂商)设备序列号
     */
    @ApiModelProperty(value ="(厂商)设备序列号")
    private String serialNumber;

    /**
     * 安装位置
     */
    @ApiModelProperty(value = "安装位置")
    private String installPosition;

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
     * 心跳间隔（秒）
     */
    @ApiModelProperty(value = "心跳间隔（秒）")
    private Integer heartBeatInterval;

    /**
     * 安装时间
     */
    @ApiModelProperty(value = "安装时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date installationTime;

    /**
     * 厂商
     */
    @ApiModelProperty(value = "厂商")
    private String provider;
}
