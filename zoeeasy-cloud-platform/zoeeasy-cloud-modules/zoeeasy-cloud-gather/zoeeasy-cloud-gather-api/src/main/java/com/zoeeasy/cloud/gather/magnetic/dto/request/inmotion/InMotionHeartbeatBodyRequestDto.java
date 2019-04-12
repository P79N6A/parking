package com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.magnetic.cst.InMotionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 推送集中器心跳主体请求参数
 *
 * @Date: 2018/9/27
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InMotionHeartbeatBodyRequestDto", description = "推送集中器心跳主体请求参数")
public class InMotionHeartbeatBodyRequestDto extends BaseDto {

    /**
     * 停车场编码
     */
    @ApiModelProperty(value = "停车场编码")
    @NotNull(message = InMotionConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    private Integer parkID;

    /**
     * 上报时间
     */
    @ApiModelProperty(value = "上报时间")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TIME_NOT_NULL)
    private String time;

    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_DEVICE_ID_NOT_NULL)
    private String deviceID;

    /**
     * 信号强度
     */
    @ApiModelProperty(value = "信号强度")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_RSSI_NOT_NULL)
    private Integer rssi;

    /**
     * 过去时间
     */
    @ApiModelProperty(value = "过去时间")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_PASS_TIME_NOT_NULL)
    private Integer passTime;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_SEQUENCE_NOT_NULL)
    private Integer sequence;

    /**
     * 电量
     */
    @ApiModelProperty(value = "电量")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_BATTERY_NOT_NULL)
    private Integer battary;

    /**
     * 设备状态异常编码
     */
    @ApiModelProperty(value = "设备状态异常编码")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_ERROR_CODE_NOT_NULL)
    private Integer errcode;

    /**
     * 占用状态
     */
    @ApiModelProperty(value = "占用状态")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_PARKING_STATUS_NOT_NULL)
    private Integer parkingStatu;

    /**
     * 校验和
     */
    @ApiModelProperty(value = "校验和")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TOKEN_NOT_NULL)
    private String token;
}
