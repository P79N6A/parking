package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 获取特殊车辆类型请求参数
 *
 * @author AkeemSuper
 * @date 2018/10/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RejectReasonRequestDto", description = "驳回原因获取请求参数")
public class SpecialTypeGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_NOT_NULL)
    private String plateNumber;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = SpecialVehicleConstant.SPECIAL_VEHICLE_PARKING_ID_NOT_NULL)
    private Long parkingId;

}
