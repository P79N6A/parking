package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/12/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingInfoGetByLocalCodeRequestDto", description = "通过客户端编号获取停车场信息")
public class ParkingInfoGetByLocalCodeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端编号
     */
    @ApiModelProperty(value = "客户端编号", required = true)
    @NotBlank(message = ParkingConstant.LOCAL_CODE_NOT_EMPTY)
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MIN_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String localCode;

}
