package com.zoeeasy.cloud.pds.magneticdetector.dto.result.airjoy;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 根据厂商和设备ID查询对应的地磁检测器响应参数
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticDetectorGetByMacResultDto", description = "根据厂商和设备ID查询对应的地磁检测器响应参数")
public class MagneticDetectorGetByMacResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 所属的区域ID
     */
    @ApiModelProperty("所属的区域ID")
    private Long areaId;

    /**
     * 泊位ID
     */
    @ApiModelProperty("泊位ID")
    private Long parkingLotId;

    /**
     * 关联地磁管理器id
     */
    @ApiModelProperty("关联地磁管理器id")
    private Long managerId;

    /**
     * 地磁检测器编号(平台唯一)
     */
    @ApiModelProperty("地磁检测器编号(平台唯一)")
    private String code;

    /**
     * 厂商
     */
    @ApiModelProperty("厂商")
    private Integer provider;

    /**
     * 电量
     */
    @ApiModelProperty("电量")
    private Integer electricity;

    /**
     * 序列号
     */
    @ApiModelProperty("序列号")
    private String serialNumber;

    /**
     * 经度
     */
    @NotBlank(message = "经度不能为空")
    @ApiModelProperty("经度")
    private Double longitude;

    /**
     * 纬度
     */
    @NotBlank(message = "纬度不能为空")
    @ApiModelProperty("纬度")
    private Double latitude;

    /**
     * 安装地址
     */
    @ApiModelProperty("安装地址")
    private String installPosition;

    /**
     * 设备版本号
     */
    @ApiModelProperty("设备版本号")
    private String versionNumber;

    /**
     * ip地址
     */
    @ApiModelProperty("ip地址")
    private String ipAddress;

    /**
     * 端口号
     */
    @ApiModelProperty("端口号")
    private Integer port;

    /**
     * SIM卡号
     */
    @ApiModelProperty("SIM卡号")
    private String simNo;

    /**
     * 是否已注册 0-未注册，1-已注册
     */
    @ApiModelProperty("是否已注册 0-未注册，1-已注册")
    private Boolean registered;

    /**
     * 最后心跳时间
     */
    @ApiModelProperty("最后心跳时间")
    private Date lastHeartbeatTime;

    /**
     * 心跳间隔时间(秒)
     */
    @ApiModelProperty("心跳间隔时间(秒)")
    private Integer heartBeatInterval;

    /**
     * 地磁检测器当前状态，-1：未知，0：正常，4：失联
     */
    @ApiModelProperty("地磁检测器当前状态，-1：未知，0：正常，4：失联")
    private Integer status;

    /**
     * 占用状态
     */
    @ApiModelProperty("占用状态")
    private Integer occupyStatus;
}
