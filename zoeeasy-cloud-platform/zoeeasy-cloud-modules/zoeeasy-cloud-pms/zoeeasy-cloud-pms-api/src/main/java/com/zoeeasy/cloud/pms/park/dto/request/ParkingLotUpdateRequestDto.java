package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 修改车位请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotUpdateRequestDto", description = "修改车位请求参数")
public class ParkingLotUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;

    /**
     * 泊车区域ID
     */
    @ApiModelProperty("泊车区域ID")
    private Long parkingAreaId;

    /**
     * 楼层id
     */
    @ApiModelProperty("楼层id")
    private Long floorId;

    /**
     * 泊位号
     */
    @ApiModelProperty(value = "泊位号")
    @Length(min = ParkingLotConstant.PARKING_LOT_NUMBER_MIN_LENGTH, max = ParkingLotConstant.PARKING_LOT_NUMBER_MAX_LENGTH, message = ParkingLotConstant.PARKING_LOT_NUMBER_LENGTH_RANGE)
    @NotBlank(message = ParkingLotConstant.PARKING_LOT_NUMBER_NOT_NULL)
    @Pattern(regexp = ParkingConstant.SPECIAL_CHARACTER_PATTERN, message = ParkingLotConstant.PARKING_LOT_NUMBER_LENGTH_RANGE)
    private String number;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(max = ParkingLotConstant.PARKING_LOT_DESCRIPTION_MAX_LENGTH, message = ParkingLotConstant.PARKING_LOT_DESCRIPTION_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.SPECIAL_CHARACTER_PATTERN, message = ParkingLotConstant.PARKING_LOT_DESCRIPTION_LENGTH_RANGE)
    private String description;

}
