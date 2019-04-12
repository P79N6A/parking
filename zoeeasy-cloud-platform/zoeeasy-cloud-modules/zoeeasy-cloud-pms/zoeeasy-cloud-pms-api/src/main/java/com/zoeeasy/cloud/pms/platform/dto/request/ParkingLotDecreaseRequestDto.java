package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 停车场可用车位数减一及车位状态变更请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotDecreaseRequestDto", description = "停车场可用车位数减一及车位状态变更请求参数")
public class ParkingLotDecreaseRequestDto extends BaseDto {

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    private Long tenantId;

    private Long parkingLotId;

    private Date occupyTime;
}
