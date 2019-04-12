package com.zoeeasy.cloud.pms.floor.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 楼层详情获取请求参数
 *
 * Created by song on 2019/3/23 10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FloorGetRequestDto", description = "楼层详情获取请求参数")
public class FloorGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @NotNull(message = PmsConstant.PARKING_ID_NOT_NULL)
    private Long id;

}
