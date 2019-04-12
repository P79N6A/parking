package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 上下架停车场请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingPutAwayRequestDto", description = "上下架停车场请求参数")
public class ParkingPutAwayRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 上下架状态: 1上架  0下架
     */
    @ApiModelProperty("上下架状态: 1上架  0下架")
    @NotNull(message = ParkingConstant.PARKING_STATUS_NOT_NULL)
    private Integer status;

}
