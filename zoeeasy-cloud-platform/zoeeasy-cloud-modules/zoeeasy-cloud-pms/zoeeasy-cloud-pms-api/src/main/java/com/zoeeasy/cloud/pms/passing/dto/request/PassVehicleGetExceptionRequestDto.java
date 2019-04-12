package com.zoeeasy.cloud.pms.passing.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassVehicleGetExceptionRequestDto", description = "获取异常过车记录请求参数")
public class PassVehicleGetExceptionRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 泊位id
     */
    @ApiModelProperty("泊位id")
    private Long parkingLotId;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 过车类型
     */
    @ApiModelProperty("过车类型")
    private Integer passingType;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    private Date passTime;
}
