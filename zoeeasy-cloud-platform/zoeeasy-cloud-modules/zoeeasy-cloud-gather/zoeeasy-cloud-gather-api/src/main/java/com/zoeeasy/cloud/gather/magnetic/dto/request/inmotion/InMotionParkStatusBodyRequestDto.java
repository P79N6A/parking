package com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 推送车位状态数据主体请求参数
 *
 * @Date: 2018/8/10
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InMotionParkStatusBodyRequestDto", description = "推送车位状态数据主体请求参数")
public class InMotionParkStatusBodyRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场编码
     */
    @ApiModelProperty(value = "停车场编码")
    private Integer parkID;

    /**
     * 车位状态变化时间
     */
    @ApiModelProperty(value = "车位状态变化时间")
    private String time;

    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    private String deviceID;

    /**
     * 信号强度
     */
    @ApiModelProperty(value = "信号强度")
    private Integer rssi;

    /**
     * 过去时间
     */
    @ApiModelProperty(value = "过去时间")
    private Integer passTime;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer sequence;

    /**
     * 电量
     */
    @ApiModelProperty(value = "电量")
    private Integer battary;

    /**
     * 占用状态
     */
    @ApiModelProperty(value = "占用状态")
    private Integer parkingStatu;

    /**
     * 校验和
     */
    @ApiModelProperty(value = "校验和")
    private String token;
}
