package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author zwq
 * @Description: 根据停车场Id获取停车场当前信息
 * @Date: 2018/09/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingCurrentInfoGetByParkingIdRequestDto", description = "根据停车场Id获取停车场当前信息")
public class ParkingCurrentInfoGetByParkingIdRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;
}
