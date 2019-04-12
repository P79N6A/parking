package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车辆所有者信息
 *
 * @author walkman
 */
@ApiModel(value = "CarOwnerGetResultDto", description = "车辆所有者信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class CarOwnerGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String username;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phoneNumber;

}
