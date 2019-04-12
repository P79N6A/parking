package com.zhuyitech.parking.inmotion.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 易姆讯心跳请求
 *
 * @Date: 2018/9/26
 * @author: zwq
 */
@ApiModel(value = "InMotionHeartBeatPushRequestDto", description = "易姆讯心跳请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class InMotionPushDataMockRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 指令编码
     */
    @ApiModelProperty(value = "指令编码,(sendParkStatu:泊位状态变更,sendDeviceHeartbeat:设备心跳)",
            allowableValues = "sendParkStatu,sendDeviceHeartbeat")
    private String cmd;

    /**
     * 主体
     */
    @ApiModelProperty(value = "主体")
    private InMotionPushDataBodyRequestDto body;
}
