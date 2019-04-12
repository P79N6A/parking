package com.zoeeasy.cloud.pms.platform.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @Description: 平台停车记录结果视图
 * @Date: 2018/3/1 0001
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordResultDto", description = "平台停车记录结果视图")
public class ParkingRecordResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty("停车记录流水号")
    private String recordNo;

    /**
     * 第三方平台停车记录ID
     */
    @ApiModelProperty("第三方平台停车记录ID")
    private String thirdParkingRecordId;

    /**
     * 支付宝平台停车记录ID
     */
    @ApiModelProperty("支付宝平台停车记录ID")
    private String aliParkingRecordId;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 海康平台停车场ID
     */
    @ApiModelProperty("海康平台停车场ID")
    private String hikParkingId;

    /**
     * 支付宝平台停车场ID
     */
    @ApiModelProperty("支付宝平台停车场ID")
    private String aliParkingId;

    /**
     * 泊位id
     */
    @ApiModelProperty("泊位id")
    private Long parkingLotId;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String parkingLotCode;

    /**
     * 海康平台泊位ID
     */
    @ApiModelProperty("海康平台泊位ID")
    private String hikParkingLotId;

    /**
     * 支付宝平台泊位ID
     */
    @ApiModelProperty("支付宝平台泊位ID")
    private String aliParkingLotId;

    /**
     * 入车记录ID
     */
    @ApiModelProperty("入车记录ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long intoRecordId;

    /**
     * 入车记录流水号
     */
    @ApiModelProperty("入车记录流水号")
    private String intoRecordNo;

    /**
     * 出车记录ID
     */
    @ApiModelProperty("出车记录ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long outRecordId;

    /**
     * 出车记录流水号
     */
    @ApiModelProperty("出车记录流水号")
    private String outRecordNo;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 停车卡号
     */
    @ApiModelProperty("停车卡号")
    private String cardNumber;

    /**
     * 停车码
     */
    @ApiModelProperty("停车码")
    private String codeNumber;

    /**
     * 停车类型
     */
    @ApiModelProperty("停车类型")
    private Integer parkingType;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer carType;

    /**
     * 停车开始时间
     */
    @ApiModelProperty("停车开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty("停车结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date endTime;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 是否预约停车
     */
    @ApiModelProperty(value = "是否预约停车")
    private Boolean appointed;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    private String appointOrderNo;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

}
