package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Date: 2019/3/19
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingByCodeGetRequestDto", description = "获取停车场请求参数")
public class ParkingByCodeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * code
     */
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    @ApiModelProperty("code")
    private String code;


}
