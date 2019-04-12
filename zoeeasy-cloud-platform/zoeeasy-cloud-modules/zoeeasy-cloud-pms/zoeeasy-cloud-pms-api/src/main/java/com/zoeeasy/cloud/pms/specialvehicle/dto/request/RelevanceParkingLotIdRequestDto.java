package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date: 2018/11/5.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RelevanceParkingLotIdRequestDto", description = "关联泊位获取请求参数")
public class RelevanceParkingLotIdRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String parkingLotNumber;

}
