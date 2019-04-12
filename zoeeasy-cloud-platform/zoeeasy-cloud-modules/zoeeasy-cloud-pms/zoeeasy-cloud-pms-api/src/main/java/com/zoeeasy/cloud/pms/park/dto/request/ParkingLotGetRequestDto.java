package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取车位请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotGetRequestDto", description = "获取车位请求参数")
public class ParkingLotGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(message = ParkingLotConstant.PARKING_LOT_ID_NOT_NULL)
    @ApiModelProperty("id")
    private Long id;

}