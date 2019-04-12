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
 * 富尚推送车位状态主体请求参数
 *
 * @Date: 2018/12/5
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FuShangParkStatusBodyRequestDto", description = "富尚推送车位状态主体请求参数")
public class FuShangParkStatusBodyRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    @NotNull(message = InMotionConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    private Integer parkID;

    /**
     * 占用状态（1占用，0空闲）
     */
    @ApiModelProperty(value = "占用状态（1占用，0空闲）")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_PARKING_STATUS_NOT_NULL)
    private Integer parkingStatu;

    /**
     * 车位状态变化时间
     */
    @ApiModelProperty(value = "车位状态变化时间")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TIME_NOT_NULL)
    private String time;

    /**
     * 设备Id
     */
    @ApiModelProperty("设备Id")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_DEVICE_ID_NOT_NULL)
    private String deviceID;

    /**
     * 信号强度
     */
    @ApiModelProperty("信号强度")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_RSSI_NOT_NULL)
    private Integer rssi;

    /**
     * 过去时间(分钟)
     */
    @ApiModelProperty("过去时间(分钟)")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_PASS_TIME_NOT_NULL)
    private Integer passTime;

    /**
     * 序号
     */
    @ApiModelProperty("序号")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_SEQUENCE_NOT_NULL)
    private Integer sequence;

    /**
     * 电池电量状态(0 正常,1 欠压,2 即将耗尽)
     */
    @ApiModelProperty("电池电量状态(0 正常,1 欠压,2 即将耗尽)")
    @NotNull(message = InMotionConstant.IN_MOTION_HEART_BEAT_BATTERY_NOT_NULL)
    private Integer battary;

    /**
     * 校验和
     */
    @ApiModelProperty("校验和")
    @NotBlank(message = InMotionConstant.IN_MOTION_HEART_BEAT_TOKEN_NOT_NULL)
    private String token;
}
