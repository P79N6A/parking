package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/12/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotInfoGetByLocalRequestDto", description = "根据客户端编号获取泊位信息")
public class ParkingLotInfoGetByLocalRequestDto extends BaseDto {

    /**
     * 客户端编号
     */
    @ApiModelProperty(value = "客户端编号")
    private String localCode;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String number;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    @NotNull(message = ParkingConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;

    /**
     * 停车场Id
     */
    @ApiModelProperty("停车场Id")
    private Long parkingId;

}
