package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 获取停车记录列表请求参数
 * @Autor: AkeemSuper
 * @Date: 2018/2/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordGetListRequestDto", description = "获取停车记录列表请求参数")
public class ParkingRecordListGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty(value = "停车记录流水号")
    private String recordNo;

    /**
     * 第三方平台停车记录ID
     */
    @ApiModelProperty(value = "第三方平台停车记录ID")
    private String thirdParkingRecordId;

    /**
     * 阿里平台停车记录ID
     */
    @ApiModelProperty(value = "阿里平台停车记录ID")
    private String aliParkingRecordId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 海康平台停车场ID
     */
    @ApiModelProperty(value = "海康平台停车场ID")
    private String hikParkingId;

    /**
     * 支付宝平台停车场ID
     */
    @ApiModelProperty(value = "支付宝平台停车场ID")
    private String aliParkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 海康平台泊位ID
     */
    @ApiModelProperty(value = "海康平台泊位ID")
    private String hikParkingLotId;

    /**
     * 支付宝平台泊位ID
     */
    @ApiModelProperty(value = "支付宝平台泊位ID")
    private String aliParkingLotId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间")
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间")
    private Date endTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
