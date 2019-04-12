package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/10/16 0016
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "parkingLotInfoGetByCodeRequestDto", description = "根据泊位编号获取泊位信息")
public class ParkingLotInfoGetByCodeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号", required = true)
    @NotBlank(message = ParkingLotConstant.PARKING_LOT_CODE_NOT_NULL)
    private String parkingLotCode;
}
