package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册支付宝成功
 *
 * @author walkman
 */
@ApiModel(value = "AlipayRegisterSuccessStatusResultDto", description = "用户注册支付宝成功")
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayRegisterSuccessStatusResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户注册支付宝成功
     */
    @ApiModelProperty("用户注册支付宝成功")
    private Boolean success;

}
