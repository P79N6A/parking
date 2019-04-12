package com.zoeeasy.cloud.gather.magnetic.dto.request.fushang;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.gather.magnetic.cst.InMotionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 富尚推送心跳主体请求参数
 *
 * @Date: 2018/12/5
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FuShangHeartBeatBodyRequestDto", description = "富尚推送心跳主体请求参数")
public class FuShangHeartBeatBodyRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    @NotNull(message = InMotionConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    private Integer parkID;

    /**
     * 占用状态（0 正常,1 异常）
     */
    @ApiModelProperty(value = "占用状态（0 正常,1 异常）")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_PARKING_STATUS_NOT_NULL)
    private Integer parkingStatu;

    /**
     * 上报时间
     */
    @ApiModelProperty(value = "上报时间")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TIME_NOT_NULL)
    private String time;

    /**
     * 设备Id
     */
    @ApiModelProperty("设备Id")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_DEVICE_ID_NOT_NULL)
    private String deviceID;

    /**
     * 电池电量状态(0 正常,1 欠压,2 即将耗尽)
     */
    @ApiModelProperty("电池电量状态(0 正常,1 欠压,2 即将耗尽)")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_BATTERY_NOT_NULL)
    private Integer battary;

    /**
     * 设备状态异常编码（0: 正常	1:传感器异常	2：电池电量
     * 低	3：信号覆盖不良）
     */
    @ApiModelProperty("设备状态异常编码（0: 正常 1:传感器异常 2：电池电量低 3：信号覆盖不良）")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_ERROR_CODE_NOT_NULL)
    private Integer errcode;

    /**
     * 校验和
     */
    @ApiModelProperty("校验和")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TOKEN_NOT_NULL)
    private String token;
}
