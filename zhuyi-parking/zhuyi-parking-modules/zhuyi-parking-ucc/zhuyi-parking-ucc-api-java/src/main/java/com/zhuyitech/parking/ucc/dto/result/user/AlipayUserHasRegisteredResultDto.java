package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户是否注册支付宝
 *
 * @author walkman
 */
@ApiModel(value = "AlipayUserHasRegisteredResultDto", description = "用户是否注册支付宝")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayUserHasRegisteredResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否注册支付宝
     */
    @ApiModelProperty("是否注册支付宝")
    private Boolean registered;

}
