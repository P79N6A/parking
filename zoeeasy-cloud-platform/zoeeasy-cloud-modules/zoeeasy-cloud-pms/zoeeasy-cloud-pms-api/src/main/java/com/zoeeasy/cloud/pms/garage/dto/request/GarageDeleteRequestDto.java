package com.zoeeasy.cloud.pms.garage.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 删除车库请求参数
 * Created by song on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarageDeleteRequestDto", description = "删除车库请求参数")
public class GarageDeleteRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull(message = GarageConstant.PARKING_GARAGE_ID_NOT_NULL)
    @ApiModelProperty("id")
    private Long id;

}
