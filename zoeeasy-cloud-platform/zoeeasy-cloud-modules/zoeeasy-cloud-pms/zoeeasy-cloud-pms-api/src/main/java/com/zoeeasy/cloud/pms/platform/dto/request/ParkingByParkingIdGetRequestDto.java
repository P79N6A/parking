package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取停车场可用车位数请求参数
 *
 * @Date: 2018/11/17
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingByParkingIdGetRequestDto", description = "获取停车场可用车位数请求参数")
public class ParkingByParkingIdGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场Id
     */
    @ApiModelProperty("停车场Id")
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;
}
