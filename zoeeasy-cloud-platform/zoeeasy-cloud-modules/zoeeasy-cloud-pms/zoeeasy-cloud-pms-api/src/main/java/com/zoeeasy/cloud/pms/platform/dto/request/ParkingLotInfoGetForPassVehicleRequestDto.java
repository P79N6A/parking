package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 通过海康平台泊位信息获取平台泊位信息
 *
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@ApiModel(value = "ParkingLotInfoGetForPassVehicleRequestDto", description = "获取车位请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingLotInfoGetForPassVehicleRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 停车场编号
     */
    @ApiModelProperty("停车场编号")
    private String parkingCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 海康平台停车场id
     */
    @ApiModelProperty("海康平台停车场id")
    private String hikParkLotId;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String berthNumber;

}
