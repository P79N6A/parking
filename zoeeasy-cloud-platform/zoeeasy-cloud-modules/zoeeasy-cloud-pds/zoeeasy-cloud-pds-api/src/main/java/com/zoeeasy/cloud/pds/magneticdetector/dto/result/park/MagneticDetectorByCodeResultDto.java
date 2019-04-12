package com.zoeeasy.cloud.pds.magneticdetector.dto.result.park;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 根据编号查询地磁参数返回
 *
 * @Date: 2018/10/13
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorByCodeResultDto", description = "MagneticDetectorResultDto")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorByCodeResultDto extends EntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器编号
     */
    @ApiModelProperty(value = "地磁检测器编号")
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

    /**
     * 最后心跳时间
     */
    @ApiModelProperty(value = "最后心跳时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date lastHeartbeatTime;

    /**
     * 心跳间隔（秒）
     */
    @ApiModelProperty(value = "心跳间隔（秒）")
    private Integer heartBeatInterval;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 -1：未知，0：正常，4：失联")
    private String status;

    /**
     * 安装位置
     */
    @ApiModelProperty(value = "安装位置")
    private String installPosition;

    /**
     * 电量
     */
    @ApiModelProperty(value = "电量")
    private String electricity;
}
