package com.zoeeasy.cloud.pds.magneticmanager.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 根据ID查询管理器参数返回
 *
 * @Date: 2018/10/25
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticManagerByIdResultDto", description = "根据ID查询管理器参数返回")
@EqualsAndHashCode(callSuper = false)
public class MagneticManagerByIdResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 管理器Id
     */
    @ApiModelProperty(value = "管理器Id")
    private Long id;

    /**
     * 管理器编号
     */
    @ApiModelProperty(value = "管理器编号")
    private String code;

    /**
     * 设备序列号
     */
    @ApiModelProperty("序列号")
    private String serialNumber;

    /**
     * 厂商
     */
    @ApiModelProperty("厂商")
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
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 安装地址
     */
    @ApiModelProperty("安装地址")
    private String installPosition;

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
}
