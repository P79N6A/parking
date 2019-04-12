package com.zhuyitech.parking.tool.dto.request.violation;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 车辆违章记录查询请求参数
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VehicleViolationQueryRequestDto", description = "车辆违章记录查询请求参数")
public class VehicleViolationQueryRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆ID
     */
    @ApiModelProperty(value = "车辆ID")
    @NotNull(message = "车辆ID不能为空")
    private Long carId;

    /**
     * 完整车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 车架号
     */
    @ApiModelProperty(value = "车架号")
    @NotBlank(message = "车架号不能为空")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty(value = "发动机号")
    @NotBlank(message = "发动机号不能为空")
    private String engineNo;

    /**
     * 车辆类型 1为小型汽车，2级为大型汽车，0 其他
     */
    @ApiModelProperty(value = "车辆类型")
    @NotNull(message = "车辆类型不能为空")
    private Integer carType;

}
