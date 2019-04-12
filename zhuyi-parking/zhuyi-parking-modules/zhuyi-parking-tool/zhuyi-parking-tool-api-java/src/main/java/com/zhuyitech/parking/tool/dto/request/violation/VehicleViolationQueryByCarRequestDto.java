package com.zhuyitech.parking.tool.dto.request.violation;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 车辆本地违章记录查询请求参数
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VehicleViolationQueryByCarRequestDto", description = "车辆违章记录查询请求参数")
public class VehicleViolationQueryByCarRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆ID
     */
    @ApiModelProperty(value = "车辆ID")
    @NotNull(message = "车辆ID不能为空")
    private Long carId;

}
