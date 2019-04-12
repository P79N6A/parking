package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/24 0024
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "parkingVehicleRecordUpDateRequestDto", description = "更新在停车辆信息请求参数")
public class ParkingVehicleRecordUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    private Date startTime;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车辆颜色
     */
    @ApiModelProperty("车辆颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private Integer carType;

    /**
     * 泊位Id
     */
    @ApiModelProperty("泊位Id")
    private Long parkingLotId;

}
