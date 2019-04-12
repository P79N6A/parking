package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 添加车位请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotAddRequest", description = "添加车位请求参数")
public class ParkingLotAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = GarageConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

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
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号", required = true)
    @NotEmpty(message = ParkingLotConstant.PARKING_LOT_CODE_NOT_NULL)
    @Length(min = ParkingLotConstant.PARKING_LOT_NUMBER_MIN_LENGTH, max = ParkingLotConstant.PARKING_LOT_NUMBER_MAX_LENGTH,
            message = ParkingLotConstant.PARKING_LOT_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingLotConstant.PARKING_LOT_CODE_PATTERN, message = ParkingLotConstant.PARKING_LOT_CODE_ILLEGAL)
    private String code;

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
