package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordGetByIntoRecordNoRequestDto", description = "根据入车记录获取停车记录请求参数")
public class ParkingRecordGetByIntoRecordNoRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = true)
    @NotNull(message = ParkingConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = PmsConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 过车记录流水号
     */
    @ApiModelProperty(value = "过车记录流水号", required = true)
    @NotBlank(message = PassingConstant.PASSING_NUMBER_NOT_EMPTY)
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String passingNo;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 过车类型
     */
    @ApiModelProperty(value = "过车类型", required = true)
    @NotNull(message = PassingConstant.PASSING_TYPE_NOT_EMPTY)
    private Integer passType;

}
