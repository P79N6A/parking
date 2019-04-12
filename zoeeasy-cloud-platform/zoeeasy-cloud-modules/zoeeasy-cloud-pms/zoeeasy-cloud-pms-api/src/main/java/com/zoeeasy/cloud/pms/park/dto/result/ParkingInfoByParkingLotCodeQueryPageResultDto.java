package com.zoeeasy.cloud.pms.park.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 根据泊位编号查询停车信息模型
 *
 * @Date: 2018/11/15
 * @author: lhj
 */
@Data
@ApiModel(value = "ParkingInfoByParkingLotCodeQueryPageResultDto", description = "根据泊位编号查询停车信息模型")
@EqualsAndHashCode(callSuper = false)
public class ParkingInfoByParkingLotCodeQueryPageResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(value = "Id")
    private Long id;

    /**
     * 泊位Id
     */
    @ApiModelProperty(value = "泊位Id")
    private Long parkingLotId;

    /**
     * 停车场Id
     */
    @ApiModelProperty(value = "停车场Id")
    private Long parkingId;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String code;

    /**
     * 泊位number
     */
    @ApiModelProperty(value = "泊位number")
    private String number;

    /**
     * 泊位状态(0-空闲, 1-占用，2-未知)
     */
    @ApiModelProperty(value = "泊位状态")
    private String status;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 入场时间
     */
    @ApiModelProperty(value = "入场时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date startTime;

    /**
     * 停车时长（分）
     */
    @ApiModelProperty(value = "停车时长")
    private Long parkPeriodTime;

    /**
     * 入车记录流水号
     */
    @ApiModelProperty(value = "入车记录流水号")
    private String intoRecordNo;

    /**
     * 入车记录ID
     */
    @ApiModelProperty(value = "入车记录ID")
    private Long intoRecordId;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;
}
