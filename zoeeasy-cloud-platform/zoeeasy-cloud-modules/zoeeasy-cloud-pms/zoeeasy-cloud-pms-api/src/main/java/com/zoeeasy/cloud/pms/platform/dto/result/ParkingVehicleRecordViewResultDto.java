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
import java.util.List;

/**
 * 停车场在停车辆视图
 *
 * @author AkeemSuper
 * @Date: 2018/3/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingVehicleRecordViewResultDto", description = "停车场在停车辆视图")
public class ParkingVehicleRecordViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 泊位ID
     */
    @ApiModelProperty("泊位ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingLotId;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String parkingLotNumber;

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
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private Integer carType;

    /**
     * 停车开始时间
     */
    @ApiModelProperty("停车开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date startTime;

    /**
     * 停车图片url集合
     */
    private List<ParkingImageViewResultDto> parkingImages;

    /**
     * 停车时长（分）
     */
    @ApiModelProperty(value = "停车时长")
    private String parkPeriodTime;
}
