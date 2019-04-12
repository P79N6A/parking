package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取停车场请求参数
 * Created by song on 2018/9/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGetRequestDto", description = "获取停车场请求参数")
public class ParkingGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    @ApiModelProperty("id")
    private Long id;

}
