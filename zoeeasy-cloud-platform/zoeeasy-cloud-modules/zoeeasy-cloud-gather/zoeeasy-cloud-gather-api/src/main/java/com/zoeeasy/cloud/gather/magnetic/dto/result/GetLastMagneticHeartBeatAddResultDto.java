package com.zoeeasy.cloud.gather.magnetic.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 授权请求参数返回
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GetLastMagneticHeartBeatAddResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 检测器ID
     */
    @ApiModelProperty(value = "检测器ID")
    private Long detectorId;

    /**
     * 地磁管理器类型(厂商)
     */
    @ApiModelProperty(value = "地磁管理器类型(厂商)")
    private Integer provider;

    /**
     * (厂商)设备序列号
     */
    @ApiModelProperty("(厂商)设备序列号")
    private String serialNumber;

    /**
     * 指令编码
     */
    @ApiModelProperty("指令编码")
    private String commandCode;

    /**
     * 心跳时间
     */
    @ApiModelProperty("心跳时间")
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
    private Integer battery;

    /**
     * 占用状态
     */
    @ApiModelProperty("占用状态")
    private Integer occupyStatus;
}
