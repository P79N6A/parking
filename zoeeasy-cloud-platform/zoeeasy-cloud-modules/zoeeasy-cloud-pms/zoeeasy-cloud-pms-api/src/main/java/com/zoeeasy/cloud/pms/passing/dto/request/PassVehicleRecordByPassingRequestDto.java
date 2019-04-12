package com.zoeeasy.cloud.pms.passing.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Date: 2019/3/27
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassVehicleRecordByPassingRequestDto", description = "修改过车记录请求参数")
public class PassVehicleRecordByPassingRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty("passingNo")
    @NotBlank(message = ParkingConstant.INTORECORD_NO_NOT_NULL)
    private String passingNo;

    /**
     * 泊位ID
     */
    @ApiModelProperty("泊位ID")
    private Long parkingLotId;

    /**
     * 泊位号
     */
    @ApiModelProperty("泊位号")
    @NotBlank(message = ParkingConstant.PARKING_LOT_NUMBER_NOT_NULL)
    @Length(max = ParkingConstant.MAX, message = ParkingConstant.LENGTH_RANGE)
    private String parkingLotNumber;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    @NotBlank(message = ParkingConstant.PARKING_LOT_CODE_NOT_NULL)
    @Length(max = ParkingConstant.MAX, message = ParkingConstant.LENGTH_RANGE)
    private String parkingLotCode;
}
