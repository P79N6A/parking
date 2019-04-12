package com.zoeeasy.cloud.pms.parkingarea.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取泊位区域请求参数
 * Created by song on 2018/9/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAreaGetRequestDto", description = "获取泊位区域请求参数")
public class ParkingAreaMyGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
    /**
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotCode;


    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

}
