package com.zoeeasy.cloud.pms.parkingarea.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 删除泊位区域请求参数
 * Created by song on 2018/9/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAreaDeleteRequestDto", description = "删除泊位区域请求参数")
public class ParkingAreaDeleteRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(message = ParkingAreaConstant.PARKING_AREA_ID_NOT_NULL)
    @ApiModelProperty("id")
    private Long id;

}
