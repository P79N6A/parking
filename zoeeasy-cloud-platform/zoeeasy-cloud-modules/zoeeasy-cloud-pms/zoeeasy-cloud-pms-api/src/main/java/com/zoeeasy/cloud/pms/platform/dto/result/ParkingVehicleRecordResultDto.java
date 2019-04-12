package com.zoeeasy.cloud.pms.platform.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车场在停车辆视图
 *
 * @author AkeemSuper
 * @Date: 2018/3/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingVehicleRecordResultDto", description = "停车场在停车辆视图")
public class ParkingVehicleRecordResultDto extends SessionEntityDto<Long> {

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

}
