package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取停车场停车记录请求参数
 *
 * @author AkeemSuper
 * @Date: 2018/3/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingVehicleRecordRequestDto", description = "获取停车场停车记录请求参数")
public class ParkingVehicleRecordGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 入车id
     */
    @ApiModelProperty(value = "入车id")
    private Long intoRecordId;

}
